
import java.awt.Image;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.Timer;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author Milan
 */
public class MainForm extends javax.swing.JFrame {

    /**
     * Creates new form MainForm
     */
    
    private final Dimension screenSize;
    private int al2br=0;
    
    public MainForm() {
        
        initComponents();
        
        lbCombine.setVisible(false);
        lbCombine.setText("");
        lbCombining.setVisible(false);
        lbCombining.setText("Combining in progress");
        lbCombineIcon.setVisible(false);
        btnReset.setVisible(false);
       
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((int)(screenSize.getWidth()/2)-(this.getWidth()/2), (int)(screenSize.getHeight()/2)-(this.getHeight()/2));
        checkButtons();
        
        lb_progress.setText("0/0");
        Object[] colums={"ID","Title","Path","Size","Resolution"};
        model.setColumnIdentifiers(colums);
        PicturesTable.setModel(model);
         
        PicturesTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        PicturesTable.getColumnModel().getColumn(1).setPreferredWidth(250);
        PicturesTable.getColumnModel().getColumn(2).setPreferredWidth(250);
        PicturesTable.getColumnModel().getColumn(3).setPreferredWidth(150);
        PicturesTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        PicturesTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        PicturesTable.setDefaultEditor(Object.class, null);
         
        al=new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                Object[] row=new Object[5];
                               
                 try
                {
                 sourceImage = ImageIO.read(pictures[br]);
                 allPictures[max_length]=pictures[br];
                 max_length++;
                 row[0]=max_length;
                 row[1]=pictures[br].getName();
                 row[2]=pictures[br].getAbsolutePath();
                 double val = (double)pictures[br].length()/1024/1024;
                 val = val*100;
                 val = Math.round(val);
                 val = val /100;
                 row[3]=val + " MB (" + pictures[br].length()/1024 + " KB)";
                 row[4]=sourceImage.getWidth()+" x "+sourceImage.getHeight();
                 model.addRow(row);

                br++;
                pb_progress.setValue(br);
                lb_progress.setText(br + "/" + pictures.length);
                System.out.println(br); 
                }    
            
                catch (IOException ex) 
                {
                    System.err.println(ex);                     
                }
                if(br>=pb_progress.getMaximum())
                {
                    t.stop();
                    buttons=true;
                }
                 
            }
                
        };
        t=new Timer(200,al);   
        
         
        
        al2=new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                 try 
                {
                    BufferedImage image = ImageIO.read(allPictures[al2br]);
                    float width=image.getWidth()*((float)slider_size.getValue()/100);
                    double height=((double)watermark.getHeight()/(double)watermark.getWidth())*width;
                    newWatermark=resize(watermark,(int)width,(int)height);
                    g2d = (Graphics2D) image.getGraphics();
                    alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Math.abs(((float)slider_opacity.getValue()-10))/10);
                    g2d.setComposite(alphaChannel);


                    topLeftX = coordinateX(locationIndex,image.getWidth(),newWatermark.getWidth());
                    topLeftY = coordinateY(locationIndex,image.getHeight(),newWatermark.getHeight());

                    g2d.drawImage(newWatermark, topLeftX, topLeftY, null);
                    System.out.println(output.getPath());

                    ImageReader reader;
                     try (ImageInputStream iis = ImageIO.createImageInputStream(allPictures[al2br])) 
                     {
                         Iterator iter = ImageIO.getImageReaders(iis);
                         reader = (ImageReader) iter.next();
                     }
                    System.out.println(reader.getFormatName());
                    finalImage=new File(output.getAbsolutePath() + "/" + allPictures[al2br].getName() + "_W" + al2br + "." + (reader.getFormatName().equals("JPEG") ? "jpg" : reader.getFormatName()));
                    ImageIO.write(image, reader.getFormatName().equals("JPEG") ? "jpg" : reader.getFormatName(), finalImage);
                    al2br++;

                    lbCombine.setText(al2br+"/"+PicturesTable.getRowCount());
                    
                    if(al2br>=PicturesTable.getRowCount())
                    {
                        t2.stop();
                        g2d.dispose();
                        buttons=true;
                        lbCombine.setVisible(false);
                        lbCombining.setText("DONE COMBINING!");
                        lbCombineIcon.setVisible(false);
                        btnReset.setVisible(true);
                        JOptionPane.showMessageDialog(null, "DONE!", "", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                catch (IOException ex) 
                {
                        System.err.println(ex);
                }
                 
                 
            }
                
        };
        
        t2=new Timer(200,al2);
        

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lbExit = new javax.swing.JLabel();
        lbMinimize = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnBrowseImages = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnBrowseWatermark = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnOutputLocation = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnCombine = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbCombine = new javax.swing.JLabel();
        lbCombining = new javax.swing.JLabel();
        btnReset = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        lbCombineIcon = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        rb_tl = new javax.swing.JRadioButton();
        rb_bl = new javax.swing.JRadioButton();
        rb_center = new javax.swing.JRadioButton();
        rb_tr = new javax.swing.JRadioButton();
        rb_br = new javax.swing.JRadioButton();
        rb_manual = new javax.swing.JRadioButton();
        rb_tc = new javax.swing.JRadioButton();
        rb_bc = new javax.swing.JRadioButton();
        rb_lc = new javax.swing.JRadioButton();
        rb_rc = new javax.swing.JRadioButton();
        pb_progress = new javax.swing.JProgressBar();
        lb_progress = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        slider_opacity = new javax.swing.JSlider();
        jScrollPane1 = new javax.swing.JScrollPane();
        PicturesTable = new javax.swing.JTable();
        btnPreviewImage = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        btnPreviewWithWatermark = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnDelete = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        slider_size = new javax.swing.JSlider();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 150));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(0, 153, 255));
        jPanel4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel4MouseDragged(evt);
            }
        });
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel4MousePressed(evt);
            }
        });
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbExit.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbExit.setForeground(new java.awt.Color(255, 255, 255));
        lbExit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbExit.setText("X");
        lbExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbExitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbExitMouseExited(evt);
            }
        });
        jPanel4.add(lbExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 0, 30, 40));

        lbMinimize.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lbMinimize.setForeground(new java.awt.Color(255, 255, 255));
        lbMinimize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMinimize.setText("-");
        lbMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbMinimizeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbMinimizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbMinimizeMouseExited(evt);
            }
        });
        jPanel4.add(lbMinimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 0, 30, 40));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 40));

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo4.png"))); // NOI18N
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 210, 100));

        btnBrowseImages.setBackground(new java.awt.Color(153, 204, 255));
        btnBrowseImages.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBrowseImagesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBrowseImagesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBrowseImagesMouseExited(evt);
            }
        });
        btnBrowseImages.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setText("Browse pictures");
        btnBrowseImages.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 140, 50));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/browse_icon.png"))); // NOI18N
        btnBrowseImages.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 50));

        jPanel2.add(btnBrowseImages, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 250, 50));

        btnBrowseWatermark.setBackground(new java.awt.Color(153, 204, 255));
        btnBrowseWatermark.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBrowseWatermarkMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBrowseWatermarkMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBrowseWatermarkMouseExited(evt);
            }
        });
        btnBrowseWatermark.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("Browse watermark");
        btnBrowseWatermark.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 150, 50));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/watermark_icon.png"))); // NOI18N
        btnBrowseWatermark.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 50));

        jPanel2.add(btnBrowseWatermark, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 250, 50));

        btnOutputLocation.setBackground(new java.awt.Color(153, 204, 255));
        btnOutputLocation.setEnabled(false);
        btnOutputLocation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOutputLocationMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnOutputLocationMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnOutputLocationMouseExited(evt);
            }
        });
        btnOutputLocation.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setText("Output location");
        btnOutputLocation.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 140, 50));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/output_icon.png"))); // NOI18N
        btnOutputLocation.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 50));

        jPanel2.add(btnOutputLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 250, 50));

        btnCombine.setBackground(new java.awt.Color(153, 204, 255));
        btnCombine.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCombineMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCombineMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCombineMousePressed(evt);
            }
        });
        btnCombine.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setText("Combine");
        btnCombine.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 140, 50));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/combine_icon.png"))); // NOI18N
        btnCombine.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 80, 70));

        jPanel2.add(btnCombine, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 540, 250, 110));

        lbCombine.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbCombine.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel2.add(lbCombine, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 210, 30));

        lbCombining.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbCombining.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCombining.setText("Combining in progress");
        jPanel2.add(lbCombining, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 250, 30));

        btnReset.setBackground(new java.awt.Color(153, 204, 255));
        btnReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnResetMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnResetMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnResetMousePressed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("RESET");
        btnReset.add(jLabel18);

        jPanel2.add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 440, 150, 30));

        lbCombineIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/progress2.gif"))); // NOI18N
        jPanel2.add(lbCombineIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 430, 70, 70));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 650));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Watermark position", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 3, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rb_tl.setBackground(new java.awt.Color(255, 255, 255));
        rbGroup.add(rb_tl);
        rb_tl.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rb_tl.setForeground(new java.awt.Color(255, 255, 255));
        rb_tl.setText("Top-Left");
        rb_tl.setOpaque(false);
        rb_tl.setPreferredSize(new java.awt.Dimension(130, 30));
        jPanel3.add(rb_tl, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 130, 30));

        rb_bl.setBackground(new java.awt.Color(255, 255, 255));
        rbGroup.add(rb_bl);
        rb_bl.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rb_bl.setForeground(new java.awt.Color(255, 255, 255));
        rb_bl.setText("Bottom-Left");
        rb_bl.setOpaque(false);
        rb_bl.setPreferredSize(new java.awt.Dimension(130, 30));
        jPanel3.add(rb_bl, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 130, 30));

        rb_center.setBackground(new java.awt.Color(255, 255, 255));
        rbGroup.add(rb_center);
        rb_center.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rb_center.setForeground(new java.awt.Color(255, 255, 255));
        rb_center.setText("Center");
        rb_center.setOpaque(false);
        rb_center.setPreferredSize(new java.awt.Dimension(130, 30));
        jPanel3.add(rb_center, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 30, 130, 30));

        rb_tr.setBackground(new java.awt.Color(255, 255, 255));
        rbGroup.add(rb_tr);
        rb_tr.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rb_tr.setForeground(new java.awt.Color(255, 255, 255));
        rb_tr.setText("Top-Right");
        rb_tr.setOpaque(false);
        rb_tr.setPreferredSize(new java.awt.Dimension(130, 30));
        jPanel3.add(rb_tr, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 130, 30));

        rb_br.setBackground(new java.awt.Color(255, 255, 255));
        rbGroup.add(rb_br);
        rb_br.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rb_br.setForeground(new java.awt.Color(255, 255, 255));
        rb_br.setText("Bottom-Right");
        rb_br.setOpaque(false);
        rb_br.setPreferredSize(new java.awt.Dimension(130, 30));
        jPanel3.add(rb_br, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 130, 30));

        rb_manual.setBackground(new java.awt.Color(255, 255, 255));
        rbGroup.add(rb_manual);
        rb_manual.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rb_manual.setForeground(new java.awt.Color(255, 255, 255));
        rb_manual.setText("Manual");
        rb_manual.setOpaque(false);
        rb_manual.setPreferredSize(new java.awt.Dimension(130, 30));
        jPanel3.add(rb_manual, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 80, 130, 30));

        rb_tc.setBackground(new java.awt.Color(255, 255, 255));
        rbGroup.add(rb_tc);
        rb_tc.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rb_tc.setForeground(new java.awt.Color(255, 255, 255));
        rb_tc.setActionCommand("Top-Center");
        rb_tc.setLabel("Top-Center");
        rb_tc.setOpaque(false);
        rb_tc.setPreferredSize(new java.awt.Dimension(130, 30));
        jPanel3.add(rb_tc, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, 130, 30));

        rb_bc.setBackground(new java.awt.Color(255, 255, 255));
        rbGroup.add(rb_bc);
        rb_bc.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rb_bc.setForeground(new java.awt.Color(255, 255, 255));
        rb_bc.setText("Bottom-Center");
        rb_bc.setOpaque(false);
        rb_bc.setPreferredSize(new java.awt.Dimension(130, 30));
        jPanel3.add(rb_bc, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, 130, 30));

        rb_lc.setBackground(new java.awt.Color(255, 255, 255));
        rbGroup.add(rb_lc);
        rb_lc.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rb_lc.setForeground(new java.awt.Color(255, 255, 255));
        rb_lc.setText("Left-Center");
        rb_lc.setOpaque(false);
        rb_lc.setPreferredSize(new java.awt.Dimension(130, 30));
        jPanel3.add(rb_lc, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 30, 130, 30));

        rb_rc.setBackground(new java.awt.Color(255, 255, 255));
        rbGroup.add(rb_rc);
        rb_rc.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rb_rc.setForeground(new java.awt.Color(255, 255, 255));
        rb_rc.setText("Right-Center");
        rb_rc.setOpaque(false);
        rb_rc.setPreferredSize(new java.awt.Dimension(130, 30));
        jPanel3.add(rb_rc, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, 130, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 340, 710, 130));

        pb_progress.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(pb_progress, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 300, 720, 20));

        lb_progress.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lb_progress.setForeground(new java.awt.Color(255, 255, 255));
        lb_progress.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_progress.setText("0/0");
        lb_progress.setAlignmentY(0.0F);
        lb_progress.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lb_progress.setIconTextGap(0);
        jPanel1.add(lb_progress, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 298, 50, 20));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Watermark opacity : ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 510, 150, 30));

        slider_opacity.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        slider_opacity.setMaximum(20);
        slider_opacity.setPaintLabels(true);
        slider_opacity.setPaintTicks(true);
        slider_opacity.setValue(0);
        slider_opacity.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        slider_opacity.setOpaque(false);
        jPanel1.add(slider_opacity, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 510, -1, 40));

        PicturesTable.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        PicturesTable.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        PicturesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        PicturesTable.setGridColor(new java.awt.Color(255, 255, 255));
        PicturesTable.setIntercellSpacing(new java.awt.Dimension(5, 5));
        PicturesTable.setRowHeight(20);
        PicturesTable.setShowHorizontalLines(false);
        PicturesTable.setShowVerticalLines(false);
        PicturesTable.getTableHeader().setResizingAllowed(false);
        PicturesTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(PicturesTable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 780, 220));

        btnPreviewImage.setBackground(new java.awt.Color(153, 204, 255));
        btnPreviewImage.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnPreviewImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPreviewImageMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPreviewImageMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPreviewImageMouseExited(evt);
            }
        });
        btnPreviewImage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Preview image");
        btnPreviewImage.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 160, 30));

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/preview_image.png"))); // NOI18N
        btnPreviewImage.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 50));

        jPanel1.add(btnPreviewImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 580, 200, 50));

        btnPreviewWithWatermark.setBackground(new java.awt.Color(153, 204, 255));
        btnPreviewWithWatermark.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPreviewWithWatermarkMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPreviewWithWatermarkMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPreviewWithWatermarkMouseExited(evt);
            }
        });
        btnPreviewWithWatermark.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Preview image with watermark");
        btnPreviewWithWatermark.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 240, 30));

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/preview_with_watermark.png"))); // NOI18N
        btnPreviewWithWatermark.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 50));

        jPanel1.add(btnPreviewWithWatermark, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 580, 300, 50));

        btnDelete.setBackground(new java.awt.Color(153, 204, 255));
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDeleteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDeleteMouseExited(evt);
            }
        });
        btnDelete.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Delete");
        btnDelete.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 70, 30));

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete_icon.png"))); // NOI18N
        btnDelete.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 50));

        jPanel1.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 580, 160, 50));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Watermark size : ");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 510, 150, 30));

        slider_size.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        slider_size.setMinimum(1);
        slider_size.setPaintLabels(true);
        slider_size.setPaintTicks(true);
        slider_size.setToolTipText("");
        slider_size.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        slider_size.setOpaque(false);
        jPanel1.add(slider_size, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 510, -1, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    int br=0;
    
    DefaultTableModel model = new DefaultTableModel();
    File[] pictures;
    int max_length=0;
    File[] allPictures=new File[1000];
    BufferedImage sourceImage=null;
    
    int topLeftX,topLeftY;
    
    private Timer t,t2;
    private final ActionListener al,al2;
    
    
   private void checkButtons()
   {
       if(allPictures.length!=0 && watermarkFile!=null && output!=null)
       {
           btnCombine.setEnabled(true);
       }
       else
       {
           btnCombine.setEnabled(false);
       }
   }
   
    private void removeFromArray(int index)
    {
        int i,j=0;
        for(i=0;i<allPictures.length;i++)
        {
            if(i!=index)
            {
                allPictures[j]=allPictures[i];
                j++;
            }
        }
    }
    
    File watermarkFile=null;   
    
    private int coordinateX(int location, int image_w, int watermark_w)
    {
        int x=0;
        switch(location)
        {
            case 1: x = 0; break;
            case 2: x = image_w - watermark_w; break;
            case 3: x = 0; break;
            case 4: x = image_w - watermark_w; break;
            case 5: x = (image_w - watermark_w)/2; break; 
            case 6: x = (image_w - watermark_w)/2; break;
            case 7: x = (image_w - watermark_w)/2; break;
            case 8: x = 0; break;
            case 9: x = image_w - watermark_w; break;
            case 10: x = (image_w - watermark_w)/2; break;
            
        }
        return x;
    }
    
     private int coordinateY(int location, int image_h, int watermark_h)
    {
        int y=0;
        switch(location)
        {
            case 1: y = 0; break;
            case 2: y = 0; break;
            case 3: y = image_h - watermark_h; break;
            case 4: y = image_h - watermark_h; break;
            case 5: y = (image_h - watermark_h)/2; break;
            case 6: y = 0; break;
            case 7: y = image_h - watermark_h; break;
            case 8: y = (image_h - watermark_h)/2; break;
            case 9: y = (image_h - watermark_h)/2; break;
            case 10: y = (image_h - watermark_h)/2; break;
        }
        return y;
    }
    
    int locationIndex=-1;
    File finalImage=null;
    
    BufferedImage newWatermark=null;
    
   private static BufferedImage resize(BufferedImage img, int width, int height) 
   {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
    
    
    File output=null;
    
        
    Color stara = new Color(153,204,255);
    Color nova = new Color(102,204,255);
    
    private void btnBrowseImagesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBrowseImagesMouseEntered
        btnBrowseImages.setBackground(nova);
    }//GEN-LAST:event_btnBrowseImagesMouseEntered

    private void btnBrowseImagesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBrowseImagesMouseExited
        btnBrowseImages.setBackground(stara);
    }//GEN-LAST:event_btnBrowseImagesMouseExited
    
    private void btnBrowseWatermarkMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBrowseWatermarkMouseEntered
        btnBrowseWatermark.setBackground(nova);
    }//GEN-LAST:event_btnBrowseWatermarkMouseEntered

    private void btnBrowseWatermarkMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBrowseWatermarkMouseExited
        btnBrowseWatermark.setBackground(stara);
    }//GEN-LAST:event_btnBrowseWatermarkMouseExited
 
    private void btnOutputLocationMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOutputLocationMouseEntered
        btnOutputLocation.setBackground(nova);
    }//GEN-LAST:event_btnOutputLocationMouseEntered

    private void btnOutputLocationMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOutputLocationMouseExited
        btnOutputLocation.setBackground(stara);
    }//GEN-LAST:event_btnOutputLocationMouseExited

    private void btnCombineMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCombineMouseEntered
        btnCombine.setBackground(nova);
    }//GEN-LAST:event_btnCombineMouseEntered

    private void btnCombineMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCombineMouseExited
        btnCombine.setBackground(stara);
    }//GEN-LAST:event_btnCombineMouseExited

    public static boolean buttons=true;
    private BufferedImage watermark=null;
    Graphics2D g2d=null;
    AlphaComposite alphaChannel=null;
    
    private void btnCombineMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCombineMousePressed
        if(buttons==true)
        {
            if(allPictures.length!=0 && watermarkFile!=null && output!=null)
            {
                buttons=false;
                radioButtons(false);
                slider_opacity.setEnabled(false);
                slider_size.setEnabled(false);
                
                if(rb_tl.isSelected()==true)
                    locationIndex=1;
                else if(rb_tr.isSelected()==true)
                    locationIndex=2;
                else if(rb_bl.isSelected()==true)
                    locationIndex=3;
                else if(rb_br.isSelected()==true)
                    locationIndex=4;
                else if(rb_center.isSelected()==true)
                    locationIndex=5;
                else if(rb_tc.isSelected()==true)
                    locationIndex=6;
                else if(rb_bc.isSelected()==true)
                    locationIndex=7;
                else if(rb_lc.isSelected()==true)
                    locationIndex=8;
                else if(rb_rc.isSelected()==true)
                    locationIndex=9;
                else if(rb_manual.isSelected()==true)
                    locationIndex=10;
                else
                {
                    JOptionPane.showMessageDialog(null, "No postition selected", "", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("No radio button selected");
                    buttons=true;
                    return;
                }
                try 
                {
                    
                    watermark = ImageIO.read(watermarkFile);
                    
                    lbCombine.setVisible(true);
                    lbCombining.setVisible(true);
                    lbCombineIcon.setVisible(true);
                    
                    t2.start();
                    } 
                catch (IOException ex) 
                {
                        System.err.println(ex);
                }   
            } 
            else
            {
                JOptionPane.showMessageDialog(null, "No watermark, pictures or output folder selected", "", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("No watermark, pictures or output folder selected");
            }
            buttons=true;
            radioButtons(true);
            slider_opacity.setEnabled(true);
            slider_size.setEnabled(true);
        }
    }//GEN-LAST:event_btnCombineMousePressed

    private void radioButtons(boolean x)
    {
        rb_bc.setEnabled(x);
        rb_bl.setEnabled(x);
        rb_br.setEnabled(x);
        rb_center.setEnabled(x);
        rb_lc.setEnabled(x);
        rb_manual.setEnabled(x);
        rb_rc.setEnabled(x);
        rb_tc.setEnabled(x);
        rb_tl.setEnabled(x);
        rb_tr.setEnabled(x);
    }
    
    final Point dragDelta = new Point();
    private void jPanel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MousePressed
        dragDelta.x = frame.getX() - evt.getXOnScreen();
        dragDelta.y = frame.getY() - evt.getYOnScreen();
    }//GEN-LAST:event_jPanel4MousePressed

    private void jPanel4MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseDragged
        frame.setLocation(evt.getXOnScreen() + dragDelta.x, evt.getYOnScreen() + dragDelta.y);
    }//GEN-LAST:event_jPanel4MouseDragged

    private void btnPreviewImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPreviewImageMouseClicked
        
        if(buttons==true)
        {
            buttons=false;
            DisplayPicture dp = new DisplayPicture();
            int sR = PicturesTable.getSelectedRow();
            if (sR == -1) {
                JOptionPane.showMessageDialog(null, "No image selected", "", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("No file selected");
            } else {

                Image newImg = null;
                dp.pack();
                dp.setBounds((int) screenSize.getWidth(), (int) screenSize.getHeight(), WIDTH, HEIGHT);
                ImageIcon img = new ImageIcon(allPictures[sR].getAbsolutePath());
                BufferedImage selectedImage = null;
                try {
                    int newWidth;
                    selectedImage = ImageIO.read(allPictures[sR]);
                    int PictureHeight = selectedImage.getHeight();
                    int PictureWidth = selectedImage.getWidth();
                    if (PictureHeight > PictureWidth) {
                        newWidth = 480;
                        double newHeight = ((double) PictureHeight / (double) PictureWidth) * newWidth;
                        newImg = img.getImage().getScaledInstance(newWidth, (int) newHeight, Image.SCALE_SMOOTH);
                        dp.setBounds((int) screenSize.getWidth() / 2 - (int) ((newWidth + 20) / 2), (int) screenSize.getHeight() / 2 - (int) ((newHeight + 50) / 2), newWidth + 20, (int) newHeight + 50);

                    } else if (PictureHeight < PictureWidth) {
                        newWidth = 640;
                        double newHeight = ((double) PictureHeight / (double) PictureWidth) * newWidth;
                        newImg = img.getImage().getScaledInstance(newWidth, (int) newHeight, Image.SCALE_SMOOTH);
                        dp.setBounds((int) screenSize.getWidth() / 2 - (int) ((newWidth + 20) / 2), (int) screenSize.getHeight() / 2 - (int) ((newHeight + 50) / 2), newWidth + 20, (int) newHeight + 50);

                    }
                    dp.setVisible(true);
                    dp.ShowImage.setIcon(new ImageIcon(newImg));

                } catch (IOException ex) {
                    System.err.println(ex);
                }
            }
        }
    }//GEN-LAST:event_btnPreviewImageMouseClicked

    private void btnPreviewWithWatermarkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPreviewWithWatermarkMouseClicked
          
        if(buttons==true)
        {
            if(watermarkFile!=null && allPictures.length!=0)
            {   
                buttons=false;
                if(rb_tl.isSelected()==true)
                    locationIndex=1;
                else if(rb_tr.isSelected()==true)
                    locationIndex=2;
                else if(rb_bl.isSelected()==true)
                    locationIndex=3;
                else if(rb_br.isSelected()==true)
                    locationIndex=4;
                else if(rb_center.isSelected()==true)
                    locationIndex=5;
                else if(rb_tc.isSelected()==true)
                    locationIndex=6;
                else if(rb_bc.isSelected()==true)
                    locationIndex=7;
                else if(rb_lc.isSelected()==true)
                    locationIndex=8;
                else if(rb_rc.isSelected()==true)
                    locationIndex=9;
                else if(rb_manual.isSelected()==true)
                    locationIndex=10;
                else
                {
                    JOptionPane.showMessageDialog(null, "No position selected", "", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("No radio button selected");
                    buttons=true;
                    return;
                }       

                DisplayPicture dp=new DisplayPicture();
                int sR=PicturesTable.getSelectedRow();
                if(sR==-1)
                 {
                    JOptionPane.showMessageDialog(null, "No image selected", "", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("No file selected");
                    buttons=true;
                    return;
                 }
                else
                {

                    Image newImg=null;
                    dp.pack();
                    dp.setLocationRelativeTo(null);           
                    BufferedImage selectedImage=null; 
                    Graphics2D g2d=null;
                    AlphaComposite alphaChannel=null;
                    try
                    {
                        int newWidth;
                        selectedImage = ImageIO.read(allPictures[sR]);
                        int PictureHeight=selectedImage.getHeight();
                        int PictureWidth=selectedImage.getWidth();

                        BufferedImage watermark = ImageIO.read(watermarkFile);
                        float width=selectedImage.getWidth()*((float)slider_size.getValue()/100);
                        double height=((double)watermark.getHeight()/(double)watermark.getWidth())*width;
                        newWatermark=resize(watermark,(int)width,(int)height);

                        topLeftX = coordinateX(locationIndex,selectedImage.getWidth(),newWatermark.getWidth());
                        topLeftY = coordinateY(locationIndex,selectedImage.getHeight(),newWatermark.getHeight());

                        g2d = (Graphics2D) selectedImage.getGraphics();
                        alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Math.abs(((float)slider_opacity.getValue()-20)/20));
                        g2d.setComposite(alphaChannel);

                        g2d.drawImage(newWatermark, topLeftX, topLeftY, null);
                        g2d.dispose();

                        ImageIcon imgIco = new ImageIcon(selectedImage);

                        if(PictureHeight>PictureWidth)
                        {
                            newWidth=480;
                            double newHeight=((double)PictureHeight/(double)PictureWidth)*newWidth;
                            newImg=imgIco.getImage().getScaledInstance(newWidth,(int)newHeight,Image.SCALE_SMOOTH);
                            dp.setBounds((int)screenSize.getWidth()/2-(int)((newWidth+20)/2), (int)screenSize.getHeight()/2-(int)((newHeight+50)/2), newWidth+20, (int)newHeight+50);

                        }
                        else if(PictureHeight<PictureWidth)
                        {
                            newWidth=640;
                            double newHeight=((double)PictureHeight/(double)PictureWidth)*newWidth;
                            newImg=imgIco.getImage().getScaledInstance(newWidth,(int)newHeight,Image.SCALE_SMOOTH);
                            dp.setBounds((int)screenSize.getWidth()/2-(int)((newWidth+20)/2), (int)screenSize.getHeight()/2-(int)((newHeight+50)/2), newWidth+20, (int)newHeight+50);

                        }
                        dp.ShowImage.setIcon(new ImageIcon(newImg));
                        dp.setVisible(true);  
                        buttons=true;
                    }
                    catch (IOException ex) 
                    {
                            System.err.println(ex);                     
                    }  

                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "No watermark or image selected", "", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("No watermark or image selected");
            }
        }
    }//GEN-LAST:event_btnPreviewWithWatermarkMouseClicked

    private void btnDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseClicked
        
        int sR=PicturesTable.getSelectedRow();
        if(sR==-1)
         {
             JOptionPane.showMessageDialog(null, "No picture selected", "", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("No file selected");
         }
        else
        {
            model.removeRow(sR);
            removeFromArray(sR);
            
        }
    }//GEN-LAST:event_btnDeleteMouseClicked

    private void btnBrowseImagesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBrowseImagesMouseClicked
        if(buttons==true)
        { 
            buttons=false;
            btnBrowseImages.setBackground(nova);
            JFileChooser file=new JFileChooser();
            file.setCurrentDirectory(new File(System.getProperty("user.home")));
            file.setDialogTitle("Select pictures");
            FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
            file.setMultiSelectionEnabled(true);
            file.setFileFilter(imageFilter);
            int result=file.showOpenDialog(null);
            if(result==JFileChooser.APPROVE_OPTION)
            {   
                pictures=file.getSelectedFiles(); 
                pb_progress.setMaximum(pictures.length);
                br=0;
                pb_progress.setValue(0);
                lb_progress.setText("0/" + pictures.length);
                checkButtons();
                t.start();
            }
            else if(result == JFileChooser.CANCEL_OPTION)
            {
                JOptionPane.showMessageDialog(null, "No images selected", "", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("No file selected");
            }
            buttons=true;            
        }
    }//GEN-LAST:event_btnBrowseImagesMouseClicked

    private void btnBrowseWatermarkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBrowseWatermarkMouseClicked
        if(buttons==true)
        {
            buttons=false;
            JFileChooser file=new JFileChooser();
            file.setCurrentDirectory(new File(System.getProperty("user.home")));
            file.setDialogTitle("Select watermark");
            FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
            file.setFileFilter(imageFilter);
            int result=file.showOpenDialog(null);
            if(result==JFileChooser.APPROVE_OPTION)
            {
                watermarkFile=file.getSelectedFile();
                checkButtons();
            }
            else if(result == JFileChooser.CANCEL_OPTION)
            {
                JOptionPane.showMessageDialog(null, "No watermark selected", "", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("No file selected");
            }
            buttons=true;
        }
    }//GEN-LAST:event_btnBrowseWatermarkMouseClicked

    private void btnOutputLocationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOutputLocationMouseClicked
        if(buttons==true)
        {
            buttons=false;
            JFileChooser chooser=new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            chooser.setDialogTitle("Select destination for output files");
            chooser.setAcceptAllFileFilterUsed(false);
            int result=chooser.showOpenDialog(null);
            if(result==JFileChooser.APPROVE_OPTION)
            {
                output=chooser.getSelectedFile();
                checkButtons();
            }
            else if(result == JFileChooser.CANCEL_OPTION)
            {
                JOptionPane.showMessageDialog(null, "No output directory selected", "", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("No directory selected");
            }
            buttons=true;
        }
    }//GEN-LAST:event_btnOutputLocationMouseClicked

    private void lbExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbExitMouseEntered
        lbExit.setForeground(Color.black);
        frame.setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_lbExitMouseEntered

    private void lbExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbExitMouseExited
        lbExit.setForeground(Color.white);
        frame.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_lbExitMouseExited

    private void lbMinimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMinimizeMouseClicked
        frame.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_lbMinimizeMouseClicked

    private void lbExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbExitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_lbExitMouseClicked

    private void lbMinimizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMinimizeMouseEntered
        lbMinimize.setForeground(Color.black);
        frame.setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_lbMinimizeMouseEntered

    private void lbMinimizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMinimizeMouseExited
        lbMinimize.setForeground(Color.white);
        frame.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_lbMinimizeMouseExited

    private void btnPreviewImageMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPreviewImageMouseEntered
        btnPreviewImage.setBackground(nova);
    }//GEN-LAST:event_btnPreviewImageMouseEntered

    private void btnPreviewImageMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPreviewImageMouseExited
        btnPreviewImage.setBackground(stara);
    }//GEN-LAST:event_btnPreviewImageMouseExited

    private void btnPreviewWithWatermarkMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPreviewWithWatermarkMouseEntered
        btnPreviewWithWatermark.setBackground(nova);
    }//GEN-LAST:event_btnPreviewWithWatermarkMouseEntered

    private void btnPreviewWithWatermarkMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPreviewWithWatermarkMouseExited
        btnPreviewWithWatermark.setBackground(stara);
    }//GEN-LAST:event_btnPreviewWithWatermarkMouseExited

    private void btnDeleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseEntered
        btnDelete.setBackground(nova);
    }//GEN-LAST:event_btnDeleteMouseEntered

    private void btnDeleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseExited
        btnDelete.setBackground(stara);
    }//GEN-LAST:event_btnDeleteMouseExited

    private void btnResetMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMousePressed
        model.setRowCount(0);
        watermark=null;
        watermarkFile=null;
        output=null;
        br=0;
        al2br=0;
    
        pictures=null;
        allPictures=null;
        max_length=0;
        allPictures=new File[1000];
        sourceImage=null;
        locationIndex=-1;
        finalImage=null;
        newWatermark=null;
        
        buttons=true;
        watermark=null;
        g2d=null;
        alphaChannel=null;
        
        slider_opacity.setValue(0);
        slider_size.setValue(50);
        
        lbCombining.setVisible(false);
        lbCombine.setText("");
        btnReset.setVisible(false);
        
        rbGroup.clearSelection();
        pb_progress.setValue(0);
    }//GEN-LAST:event_btnResetMousePressed

    private void btnResetMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMouseEntered
        btnReset.setBackground(nova);
    }//GEN-LAST:event_btnResetMouseEntered

    private void btnResetMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMouseExited
        btnReset.setBackground(stara);
    }//GEN-LAST:event_btnResetMouseExited


    
    /**
     * @param args the command line arguments
     */
    private static MainForm frame;
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            frame = new MainForm();
            frame.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable PicturesTable;
    private javax.swing.JPanel btnBrowseImages;
    private javax.swing.JPanel btnBrowseWatermark;
    private javax.swing.JPanel btnCombine;
    private javax.swing.JPanel btnDelete;
    private javax.swing.JPanel btnOutputLocation;
    private javax.swing.JPanel btnPreviewImage;
    private javax.swing.JPanel btnPreviewWithWatermark;
    private javax.swing.JPanel btnReset;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbCombine;
    private javax.swing.JLabel lbCombineIcon;
    private javax.swing.JLabel lbCombining;
    private javax.swing.JLabel lbExit;
    private javax.swing.JLabel lbMinimize;
    private javax.swing.JLabel lb_progress;
    public javax.swing.JProgressBar pb_progress;
    private javax.swing.ButtonGroup rbGroup;
    private javax.swing.JRadioButton rb_bc;
    private javax.swing.JRadioButton rb_bl;
    private javax.swing.JRadioButton rb_br;
    private javax.swing.JRadioButton rb_center;
    private javax.swing.JRadioButton rb_lc;
    private javax.swing.JRadioButton rb_manual;
    private javax.swing.JRadioButton rb_rc;
    private javax.swing.JRadioButton rb_tc;
    private javax.swing.JRadioButton rb_tl;
    private javax.swing.JRadioButton rb_tr;
    private javax.swing.JSlider slider_opacity;
    private javax.swing.JSlider slider_size;
    // End of variables declaration//GEN-END:variables
}
