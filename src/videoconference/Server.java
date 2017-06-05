/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoconference;

import AudioThread.ServerAudio;
import VideoThread.ServerThread;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author ACER
 */
public class Server extends javax.swing.JFrame {
 ArrayList clientOutputStreams;
   ArrayList<String> users;
   ArrayList clientVideoOutputStreams;
   public static boolean connnected=false;
   Socket clientSock;
    int port=2222;
    int audioPort=1111;
    /**
     * Creates new form Server
     */
    public Server() {
        initComponents();
    }
/////////////////////////////////
    public JPanel getJPanel()
    {
        return jPanel1;
    }
////////////////////////////////
    public JTextArea getJText()
    {
        return txtThongBaoServer;
    }
///////////////////////////////
      public void init_audio(){
        try{
            ServerAudio svAudio = new ServerAudio();
            svAudio.port= audioPort;
            svAudio.din =  new DatagramSocket(audioPort);
            Thread t = new Thread(svAudio);
            t.start();
           
        }catch(Exception e){
       
        }
    }
    
//////////////////////////////
    public class ServerStart implements Runnable 
    {
        @Override
        public void run() 
        {
            clientOutputStreams = new ArrayList();
            clientVideoOutputStreams=new ArrayList();
            users = new ArrayList();  

            try 
            {
                ServerSocket serverSock = new ServerSocket(port);

                while (true) 
                {
                                 clientSock = serverSock.accept();
                                 // Server.connnected=true;
				PrintWriter writer = new PrintWriter(clientSock.getOutputStream());
				clientOutputStreams.add(writer);
                                clientVideoOutputStreams.add(clientSock);
				Thread listener = new Thread(new ClientHandler(clientSock, writer));
				listener.start();
				txtThongBaoServer.append("Nhận được kết nối. \n");
                }
            }
            catch (Exception ex)
            {
               txtThongBaoServer.append("Lỗi kết nối!. \n");
                 java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
    }
////////////////////////////////
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtThongBaoServer = new javax.swing.JTextArea();
        btnStart = new javax.swing.JButton();
        btnKetThuc = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtThongBaoServer.setEditable(false);
        txtThongBaoServer.setColumns(20);
        txtThongBaoServer.setRows(5);
        jScrollPane1.setViewportView(txtThongBaoServer);

        btnStart.setText("Khởi động");
        btnStart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStartMouseClicked(evt);
            }
        });

        btnKetThuc.setText("Kết thúc");
        btnKetThuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnKetThucMouseClicked(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 249, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 194, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnKetThuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(btnStart)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnKetThuc))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStartMouseClicked
      //////////////////////////////////
        Thread starter = new Thread(new ServerStart());
        starter.start();
        
        txtThongBaoServer.append("Máy chủ đã khởi động ,Port = "+port+ "\n");
        txtThongBaoServer.append("Đang chờ ..............."+"\n");
        Thread t=new ServerThread(this);
        t.start();
      ////////////////////////////////
      init_audio();
      ///////////////////////////////
    }//GEN-LAST:event_btnStartMouseClicked

    private void btnKetThucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKetThucMouseClicked
          try 
        {
            Thread.sleep(5000);                 //5000 milliseconds is five second.
        } 
        catch(InterruptedException ex) {Thread.currentThread().interrupt();}
        
        tellEveryone("Server:đang dừng và các kết nối sẽ bị ngắt:Chat");
        txtThongBaoServer.append("Server đang dừng... \n");
        
        
    }//GEN-LAST:event_btnKetThucMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Server().setVisible(true);
            }
        });
    }
 public class ClientHandler implements Runnable	
   {
       BufferedReader reader;
       Socket sock;
       PrintWriter client;
       
       public ClientHandler(Socket clientSocket, PrintWriter user) 
       {
            client = user;
          
            try 
            {
                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
            }
            catch (Exception ex) 
            {
                txtThongBaoServer.append("Unexpected error... \n");
            }

       }

       @Override
       public void run() 
       {
            String message, connected = "Connected", disconnect = "Disconnect", chat = "Chat" ;
            String[] data;

            try 
            {
                while ((message= reader.readLine()) != null) 
                {
                    txtThongBaoServer.append("Đã nhận : " + message + "\n");
                    data = message.split(":");
                    
                   // for (String token:data) 
                   // {
                    //    txtThongBaoServer.append(token + "\n");
                  //  }
                    if (data[2].equals(chat)) 
                    {
                       tellEveryone(message);
                    } 
                    if (data[1].equals(connected)) 
                    {
                        tellEveryone((data[0] + ":" + data[1] + ":" + connected));
                       // userAdd(data[0]);
                    } 
                   /* else if (data[2].equals(disconnect)) 
                    {
                       // tellEveryone((data[0] + ":has disconnected." + ":" + chat));
                        //userRemove(data[0]);
                    } 
                    else if (data[2].equals(chat)) 
                    {
                        //tellEveryone(message);
                    } 
                    else 
                    {
                        txtThongBaoServer.append("No Conditions were met. \n");
                        //java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }*/
                } 
             } 
             catch (Exception ex) 
             {
                txtThongBaoServer.append("Mất kết nối! \n");
                  java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                ex.printStackTrace();
                clientOutputStreams.remove(client);
             } 
	} 

       
    }
 //////////////////
  private void tellEveryone(String message) {
          Iterator it = clientOutputStreams.iterator();

        while (it.hasNext()) 
        {
            try 
            {
                PrintWriter writer = (PrintWriter) it.next();
		writer.println(message);
                
		txtThongBaoServer.append("Đang gửi: " + message + "\n");
                writer.flush();
                txtThongBaoServer.setCaretPosition(txtThongBaoServer.getDocument().getLength());

            } 
            catch (Exception ex) 
            {
		txtThongBaoServer.append("Lỗi: telling everyone. \n");
            }
        } 
        }
 //////////////////
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKetThuc;
    private javax.swing.JButton btnStart;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtThongBaoServer;
    // End of variables declaration//GEN-END:variables
}
