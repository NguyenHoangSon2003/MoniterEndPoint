package jsch;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author hson9
 */
public class jsch extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String host = request.getParameter("host");
            String command = request.getParameter("command");

            JSch jsch = new JSch();
            Session session = null;
            ChannelExec channel = null;

            try {
                //Thiết Lập Phiên SSH:
                //Tại đây, một phiên SSH mới được tạo bằng thông tin đăng nhập đã cung cấp. 
                //Cấu hình StrictHostKeyChecking được đặt là "no" để bỏ qua việc kiểm tra khóa máy chủ. 
                //Phiên được thiết lập bằng phương thức connect().
                session = jsch.getSession(username, host, 22);
                session.setPassword(password);
                session.setConfig("StrictHostKeyChecking", "no");
                session.connect();
                //Một kênh "exec" được mở để thực thi lệnh trên máy chủ từ xa. Lệnh được đặt bằng setCommand(), 
                //và đầu ra được ghi vào một ByteArrayOutputStream.

                channel = (ChannelExec) session.openChannel("exec");
                channel.setCommand(command);
                InputStream inputStream = channel.getInputStream();
                channel.connect();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                StringBuilder responseString = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    responseString.append(line).append("<br>");
                }
                out.println("<h1>" + responseString.toString() + "</h1>");

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (session != null) {
                    session.disconnect();
                }
                if (channel != null) {
                    channel.disconnect();
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
