package SSHConnetion;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class threadsSSH {

    public static void main(String[] args) {
        // Danh sách thông tin máy chủ
        String[][] servers = {
            {"192.168.0.113", "takashi", "123456"},
            {"192.168.0.120", "takashi", "123456"},
        };

        // Lệnh cần chạy
        String command = "ls -l";

        // Sử dụng một ExecutorService để quản lý các luồng
        ExecutorService executor = Executors.newFixedThreadPool(servers.length);

        for (String[] server : servers) {
            String host = server[0];
            String user = server[1];
            String password = server[2];

            executor.submit(() -> {
                try {
                    System.out.println("Kết nối tới: " + host);
                    connectAndExecuteCommand(host, user, password, command);
                } catch (Exception e) {
                    System.err.println("Lỗi khi kết nối tới " + host + ": " + e.getMessage());
                }
            });
        }

        // Đóng ExecutorService
        executor.shutdown();
    }

    public static void connectAndExecuteCommand(String host, String user, String password, String command) throws Exception {
        JSch jsch = new JSch();
        Session session = jsch.getSession(user, host, 22);
        session.setPassword(password);

        // Cấu hình để bỏ qua xác thực HostKey
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);

        // Kết nối
        session.connect();

        // Mở channel để thực thi lệnh
        ChannelExec channel = (ChannelExec) session.openChannel("exec");
        channel.setCommand(command);
        channel.setInputStream(null);
        channel.setErrStream(System.err);

        InputStream inputStream = channel.getInputStream();
        channel.connect();

        // Đọc đầu ra lệnh
        byte[] tmp = new byte[1024];
        while (true) {
            while (inputStream.available() > 0) {
                int i = inputStream.read(tmp, 0, 1024);
                if (i < 0) {
                    break;
                }
                System.out.print("[" + host + "] " + new String(tmp, 0, i));
            }
            if (channel.isClosed()) {
                System.out.println("[" + host + "] Exit status: " + channel.getExitStatus());
                break;
            }
            Thread.sleep(1000);
        }

        // Đóng kết nối
        channel.disconnect();
        session.disconnect();
    }
}
