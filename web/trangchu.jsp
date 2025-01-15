
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String host = (String) session.getAttribute("host");
    String username = (String) session.getAttribute("username");
    String os  = (String) session.getAttribute("os");
    String kernel = (String) session.getAttribute("kernel");
    String cpu = (String) session.getAttribute("cpu");
    String ram = (String) session.getAttribute("ram");
    String disk = (String) session.getAttribute("disk");
    String[] svstatus = (String[]) session.getAttribute("svstatus");
    

%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            table {
                width: auto; /* Giới hạn chiều rộng bảng */
                border-collapse: collapse; /* Gộp viền giữa các ô */
            }
            th, td {
                text-align: left;
                padding: 8px;
                font-size: 30px;
            }
        </style>
    </head>
    <body>
        <h1>Cấu hình máy tính</h1>
        <table>
            <tr>
                <th>IP:</th>
                <td><%= host %></td>
            </tr>
            <tr>
                <th>Username:</th>
                <td><%= username %></td>
            </tr>
            <tr>
                <th>Operating System:</th>
                <td><%=  os%></td>
            </tr>
            <tr>
                <th>CPU:</th>
                <td><%=  cpu%></td>
            </tr>
            <tr>
                <th>Kernel:</th>
                <td><%=  kernel%></td>
            </tr>
            <tr>
                <th>Ram:</th>
                <td><%= ram%></td>
            </tr>
            <tr>
                <th>Disk:</th>
                <td><%= disk%></td>
            </tr>
        </table>  
        <h1>Dịch vụ</h1>
        <table>
            <tr>
                <th>HTTP:</th>
                <td><%= svstatus[0]%></td>
            </tr>
            <tr>
                <th>SSH:</th>
                <td><%= svstatus[1]%></td>
            </tr>
            <tr>
                <th>Samba:</th>
                <td><%= svstatus[2]%></td>
            </tr>
            <tr>
                <th>FTP:</th>
                <td><%=  svstatus[3]%></td>
            </tr>
            <tr>
                <th>My SQL:</th>
                <td><%=  svstatus[4]%></td>
            </tr>
            <tr>
                <th>SMTP:</th>
                <td><%= svstatus[5] %></td>
            </tr>
            <tr>
                <th>UFW:</th>
                <td><%= svstatus[6] %></td>
            </tr>
            <tr>
                <th>DHCP:</th>
                <td><%= svstatus[7] %></td>
            </tr>
            <tr>
                <th>DNS:</th>
                <td><%= svstatus[8] %></td>
            </tr>
        </table>

    </body>
</html>

