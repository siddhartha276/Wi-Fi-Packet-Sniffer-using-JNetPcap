import org.jnetpcap.Pcap;
import org.jnetpcap.PcapDumper;
import org.jnetpcap.PcapIf;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;
import java.util.ArrayList;
import java.util.List;

public class PacketCapture {

    public static void main(String[] args) {
        List<PcapIf> allDevices = new ArrayList<>();
        StringBuilder errorBuffer = new StringBuilder();

        // Get list of network devices
        int resultCode = Pcap.findAllDevs(allDevices, errorBuffer);
        if (resultCode != Pcap.OK || allDevices.isEmpty()) {
            System.err.printf("Can't read list of devices, error: %s\n", errorBuffer.toString());
            return;
        }

        System.out.println("Network devices found:");
        PcapIf wifiDevice = null;

        // List all devices and look for the Wi-Fi adapter
        for (int i = 0; i < allDevices.size(); i++) {
            PcapIf device = allDevices.get(i);
            String description = (device.getDescription() != null) ? device.getDescription() : "No description available";
            System.out.printf("#%d: %s [%s]\n", i, device.getName(), description);

            // Check if the device is the Wi-Fi adapter (modify this check as needed)
            if (description.toLowerCase().contains("wi-fi") || description.toLowerCase().contains("wireless")) {
                wifiDevice = device;
            }
        }

        // If Wi-Fi adapter was not found, exit
        if (wifiDevice == null) {
            System.err.println("No Wi-Fi adapter found. Please ensure you are connected to a Wi-Fi network.");
            return;
        }

        System.out.printf("Using Wi-Fi device: %s [%s]\n", wifiDevice.getName(), wifiDevice.getDescription());

        // Set capture parameters
        int snaplen = 64 * 1024; // Capture all packets, no truncation
        int flags = Pcap.MODE_NON_PROMISCUOUS; // Non Promiscuous mode
        int timeout = 60 * 1000; // 1-minute timeout

        // Open the Wi-Fi device for capturing
        Pcap pcap = Pcap.openLive(wifiDevice.getName(), snaplen, flags, timeout, errorBuffer);
        if (pcap == null) {
            System.err.printf("Error while opening device for capture: %s\n", errorBuffer.toString());
            return;
        }

        // Set up the output file for saving packets
        String currentDir = System.getProperty("user.dir");
        String outputFilename = currentDir + "/captured_packets(1).pcap";
        PcapDumper dumper = pcap.dumpOpen(outputFilename);

        if (dumper == null) {
            System.err.printf("Error while opening output file for capture: %s\n", pcap.getErr());
            pcap.close();
            return;
        }

        // Create a packet handler
        PcapPacketHandler<PcapDumper> packetHandler = new PcapPacketHandler<PcapDumper>() {
            private int packetCount = 0;

            public void nextPacket(PcapPacket packet, PcapDumper dumper) {
                System.out.println("Packet captured!"); // Debug print
                dumper.dump(packet);
                packetCount++;
                System.out.printf("Captured packet #%d: caplen=%-4d len=%-4d\n",
                        packetCount, packet.getCaptureHeader().caplen(), packet.getCaptureHeader().wirelen());
                if (packetCount >= 1000) {
                    pcap.breakloop();
                }
            }
        };

        // Start capturing packets
        pcap.loop(Pcap.LOOP_INFINITE, packetHandler, dumper);
        dumper.close();
        pcap.close();

        System.out.println("Packet capture complete. Saved to " + outputFilename);
    }
}
