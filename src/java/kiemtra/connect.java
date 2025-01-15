package kiemtra;

import chuoi.tachchuoi;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jsch.thongtintrave;

public class connect extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession ss = request.getSession();

            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String host = request.getParameter("host");

            thongtintrave tttv = new thongtintrave();
            String kq = tttv.thongtintrave(username, password, host, "hostnamectl");
            tachchuoi tc = new tachchuoi();
            String os = tc.extractValue(kq, "Operating System");
            String kernel = tc.extractValue(kq, "Kernel");

            String kqtimcpu = tttv.thongtintrave(username, password, host, "lscpu");
            String cpu = tc.extractValue(kqtimcpu, "Model name");

            String kqtimram = tttv.thongtintrave(username, password, host, "free -h");
            String ram = tc.extractValueSpace(kqtimram, "Mem: ") + " Gi";

            String kqtimdisk = tttv.thongtintrave(username, password, host, "lsblk");
            String disk = tc.extractValueSpace(kqtimdisk, "sda      8:0    0  ") + " G";

            String[] services = {
                "httpd", // HTTP
                "sshd", // SSH
                "smbd", // Samba
                "vsftpd", // FTP
                "mysqld", // MySQL
                "postfix", // SMTP
                "ufw", // UFW
                "isc-dhcp-server", // DHCP
                "named" // DNS
            };
            String[] serviceStatuses = new String[services.length];

            for (int i = 0; i < services.length; i++) {
                String service = services[i];
                String command = "systemctl is-active " + service;
                String kqtrangthai = tttv.thongtintrave(username, password, host, command);
                serviceStatuses[i] = kqtrangthai; 
//                out.println(service + " status: " + kqtrangthai); 
            }
            ss.setAttribute("username", username);
            ss.setAttribute("password", password);
            ss.setAttribute("host", host);
            ss.setAttribute("os", os);
            ss.setAttribute("kernel", kernel);
            ss.setAttribute("cpu", cpu);
            ss.setAttribute("ram", ram);
            ss.setAttribute("disk", disk);
            ss.setAttribute("svstatus", serviceStatuses);

            response.sendRedirect("trangchu.jsp");
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
