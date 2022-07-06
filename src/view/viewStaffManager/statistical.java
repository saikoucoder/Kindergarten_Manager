/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.viewStaffManager;

import controller.ChildrenListener;
import controller.StatisticalListerner;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import model.Children;
import model.IntroduceChildren;
import model.LocalTime;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.*;
import org.jfree.data.general.*;
import org.jfree.data.statistics.HistogramDataset;

/**
 *
 * @author ADMIN
 */
public class statistical extends javax.swing.JFrame {

    /**
     * Creates new form ư
     */
    private static int numOfChildCurent, numOfChildIntroduce, numOfChildAdopted;
    private ChildrenListener child = new ChildrenListener();
    private StatisticalListerner abc = new StatisticalListerner();

    public statistical() {
        initComponents();
        
        setMenu(jlb11);
        String a = LocalTime.getDateNow();
        String[] b = a.split("-");
//      
        ChildTable("2022");
        CBNVTable("2022");
        NVQLTbale("2022");
        
        DeviceTable("2022");
        ChildyearButton();
        LoadForm();

    }

 
 
    
    private void ChildyearButton() {
        ArrayList<String> all = abc.ChildYearTable();
        String[] year = new String[all.size()];
        for (int i = 0; i < all.size(); i++) {
            year[i] = all.get(i);
        }
        ChildButton.setModel(new DefaultComboBoxModel(year));

    }
    
    private  void DeviceTable(String year){
        DefaultCategoryDataset dataset = abc.DevicePrice(year);
        JFreeChart linechart = ChartFactory.createLineChart("Price Device", "Month", "amount(1k)",
                dataset, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot lineCategoryPlot = linechart.getCategoryPlot();
        //lineCategoryPlot.setRangeGridlinePaint(Color.BLUE);
        lineCategoryPlot.setBackgroundPaint(Color.white);
        LineAndShapeRenderer lineRenderer = (LineAndShapeRenderer) lineCategoryPlot.getRenderer();
        Color lineChartColor = new Color(204, 0, 51);
        lineRenderer.setSeriesPaint(0, lineChartColor);
        ChartPanel lineChartPanel = new ChartPanel(linechart);
        LinePriceDevice.removeAll();
        LinePriceDevice.add(lineChartPanel, BorderLayout.CENTER);
        LinePriceDevice.validate();
    }
    
    public void ChildTable(String year){
        // gender cua tre
        DefaultPieDataset barDataset = new DefaultPieDataset();
        barDataset = abc.Gender(year);
        JFreeChart piechart = ChartFactory.createPieChart("GENDER CHILD", barDataset, false, true, false);
        PiePlot piePlot = (PiePlot) piechart.getPlot();
        piePlot.setBackgroundPaint(Color.white);
        ChartPanel barChartPanel = new ChartPanel(piechart);
        pieChartGender.removeAll();
        pieChartGender.add(barChartPanel, BorderLayout.CENTER);
        pieChartGender.validate();
                
        //agechild
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset = abc.AgeChild(year);
        JFreeChart linechart = ChartFactory.createLineChart("Children Age", "Age", "amount",
                dataset, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot lineCategoryPlot = linechart.getCategoryPlot();
        //lineCategoryPlot.setRangeGridlinePaint(Color.BLUE);
        lineCategoryPlot.setBackgroundPaint(Color.white);
        LineAndShapeRenderer lineRenderer = (LineAndShapeRenderer) lineCategoryPlot.getRenderer();
        Color lineChartColor = new Color(204, 0, 51);
        lineRenderer.setSeriesPaint(0, lineChartColor);
        ChartPanel lineChartPanel = new ChartPanel(linechart);
        LineChartAge.removeAll();
        LineChartAge.add(lineChartPanel, BorderLayout.CENTER);
        LineChartAge.validate();
        
        //tre hien tai
        DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
        dataset1 = abc.PresentChild(year);

        JFreeChart chart1 = ChartFactory.createBarChart("Children Introduce", "monthly", "amount",
                dataset1, PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot categoryPlot1 = chart1.getCategoryPlot();
        //categoryPlot.setRangeGridlinePaint(Color.BLUE);
        categoryPlot1.setBackgroundPaint(Color.WHITE);
        BarRenderer renderer = (BarRenderer) categoryPlot1.getRenderer();
        Color clr3 = new Color(204, 0, 51);
        renderer.setSeriesPaint(0, clr3);

        ChartPanel barpChartPanel = new ChartPanel(chart1);
        barchartChild.removeAll();
        barchartChild.add(barpChartPanel, BorderLayout.CENTER);
        barchartChild.validate();

        
    }
    
    public void CBNVTable(String year)
    {
        //CBNV
        DefaultPieDataset barDataset = new DefaultPieDataset();
        barDataset = abc.QuitOrNotCBNV(year);
        JFreeChart piechart = ChartFactory.createPieChart("CBNV", barDataset, false, true, false);
        PiePlot piePlot = (PiePlot) piechart.getPlot();
        piePlot.setBackgroundPaint(Color.white);
        ChartPanel barChartPanel= new ChartPanel(piechart);
        CBNVChart.removeAll();
        CBNVChart.add(barChartPanel, BorderLayout.CENTER);
        CBNVChart.validate();
    }
    
    public void NVQLTbale(String year){
        DefaultPieDataset barDataset = new DefaultPieDataset();
        barDataset = abc.QuitOrNotNVQL(year);
        JFreeChart piechart = ChartFactory.createPieChart("NVQL", barDataset, false, true, false);
        PiePlot piePlot = (PiePlot) piechart.getPlot();
        piePlot.setBackgroundPaint(Color.white);
        ChartPanel barChartPanel = new ChartPanel(piechart);
        NVQLChart.removeAll();
        NVQLChart.add(barChartPanel, BorderLayout.CENTER);
        NVQLChart.validate();
    }
    
    public void PieChart(String year) {
        // gender cua tre
        DefaultPieDataset barDataset = new DefaultPieDataset();
        barDataset = abc.Gender(year);
        JFreeChart piechart = ChartFactory.createPieChart("GENDER CHILD", barDataset, false, true, false);
        PiePlot piePlot = (PiePlot) piechart.getPlot();
        piePlot.setBackgroundPaint(Color.white);
        ChartPanel barChartPanel = new ChartPanel(piechart);
        pieChartGender.removeAll();
        pieChartGender.add(barChartPanel, BorderLayout.CENTER);
        pieChartGender.validate();

        //CBNV
        barDataset = abc.QuitOrNotCBNV(year);
        piechart = ChartFactory.createPieChart("CBNV", barDataset, false, true, false);
        piePlot = (PiePlot) piechart.getPlot();
        piePlot.setBackgroundPaint(Color.white);
        barChartPanel = new ChartPanel(piechart);
        CBNVChart.removeAll();
        CBNVChart.add(barChartPanel, BorderLayout.CENTER);
        CBNVChart.validate();

        //NVQL
        barDataset = abc.QuitOrNotNVQL(year);
        piechart = ChartFactory.createPieChart("NVQL", barDataset, false, true, false);
        piePlot = (PiePlot) piechart.getPlot();
        piePlot.setBackgroundPaint(Color.white);
        barChartPanel = new ChartPanel(piechart);
        NVQLChart.removeAll();
        NVQLChart.add(barChartPanel, BorderLayout.CENTER);
        NVQLChart.validate();
    }

    public void LineChart(String year) {
        //agechild
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset = abc.AgeChild(year);
        JFreeChart linechart = ChartFactory.createLineChart("contribution", "Age", "amount",
                dataset, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot lineCategoryPlot = linechart.getCategoryPlot();
        //lineCategoryPlot.setRangeGridlinePaint(Color.BLUE);
        lineCategoryPlot.setBackgroundPaint(Color.white);
        LineAndShapeRenderer lineRenderer = (LineAndShapeRenderer) lineCategoryPlot.getRenderer();
        Color lineChartColor = new Color(204, 0, 51);
        lineRenderer.setSeriesPaint(0, lineChartColor);
        ChartPanel lineChartPanel = new ChartPanel(linechart);
        LineChartAge.removeAll();
        LineChartAge.add(lineChartPanel, BorderLayout.CENTER);
        LineChartAge.validate();

        //DevicePrice        
        dataset = abc.DevicePrice(year);
        linechart = ChartFactory.createLineChart("contribution", "Month", "amount(1k)",
                dataset, PlotOrientation.VERTICAL, false, true, false);
        lineCategoryPlot = linechart.getCategoryPlot();
        //lineCategoryPlot.setRangeGridlinePaint(Color.BLUE);
        lineCategoryPlot.setBackgroundPaint(Color.white);
        lineRenderer = (LineAndShapeRenderer) lineCategoryPlot.getRenderer();
        lineChartColor = new Color(204, 0, 51);
        lineRenderer.setSeriesPaint(0, lineChartColor);
        lineChartPanel = new ChartPanel(linechart);
        LinePriceDevice.removeAll();
        LinePriceDevice.add(lineChartPanel, BorderLayout.CENTER);
        LinePriceDevice.validate();
    }

    public void barchar(String year) {
        //tre hien tai
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset = abc.PresentChild(year);

        JFreeChart chart = ChartFactory.createBarChart("contribution", "monthly", "amount",
                dataset, PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot categoryPlot = chart.getCategoryPlot();
        //categoryPlot.setRangeGridlinePaint(Color.BLUE);
        categoryPlot.setBackgroundPaint(Color.WHITE);
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        Color clr3 = new Color(204, 0, 51);
        renderer.setSeriesPaint(0, clr3);

        ChartPanel barpChartPanel = new ChartPanel(chart);
        barchartChild.removeAll();
        barchartChild.add(barpChartPanel, BorderLayout.CENTER);
        barchartChild.validate();

    }

//    void LoadForm() {
//        ArrayList<Children> list = child.getListChildren(child.proShowChild(2));
//        numOfChildCurent = list.size();
//        jlb1.setText("Tổng số trẻ hiện tại trong trung tâm:  " + String.valueOf(numOfChildCurent));
//        ArrayList<IntroduceChildren> list1 = new ArrayList<>();
//        list1 = child.getListChildrenIntroduced(child.proShowChildIntro());
//        numOfChildIntroduce = list1.size();
//        jlb2.setText("Tổng số được giới thiệu vào trung tâm:  " + String.valueOf(numOfChildIntroduce));
//        numOfChildAdopted = child.numOfChildAdopted();
//        jlb3.setText("Số trẻ đã được nhận nuôi: " + String.valueOf(numOfChildAdopted));
//    }
    public void showSupport(ArrayList<String> list) {
        DefaultTableModel model = (DefaultTableModel) jtbRank.getModel();
        model.setRowCount(0);
        for (int i = 0; i < list.size(); i++) {
            String[] tmp = list.get(i).split(";");
            model.addRow(new Object[]{
                String.valueOf(i+1), tmp[0], tmp[1]
            });
        }
        jtbRank.setModel(model);
    }

    void LoadForm() {
        ArrayList<Children> list = child.getListChildren(child.proShowChild(2));
        numOfChildCurent = list.size();
        jlb1.setText("Tổng số trẻ hiện tại trong trung tâm:  " + String.valueOf(numOfChildCurent));
        ArrayList<IntroduceChildren> list1 = new ArrayList<>();
        list1 = child.getListChildrenIntroduced(child.proShowChildIntro());
        numOfChildIntroduce = list1.size();
        jlb2.setText("Tổng số được giới thiệu vào trung tâm:  " + String.valueOf(numOfChildIntroduce));
        numOfChildAdopted = child.numOfChildAdopted();
        jlb3.setText("Số trẻ đã được nhận nuôi: " + String.valueOf(numOfChildAdopted));
        jlb5.setText("Tổng NVQL:    " + String.valueOf(abc.SoLuongNV(1)));
        jlb4.setText("Tổng CBNV:    " + String.valueOf(abc.SoLuongNV(2)));
        jlb7.setText("Tổng hoạt động từ thiện:   " + String.valueOf(abc.SoLuongHoatDong(1)));
        jlb8.setText("Tổng hoạt động ngoại khóa:    " + String.valueOf(abc.SoLuongHoatDong(2)));
        jlb6.setText("Tổng tiền mua trang thiết bị:   " + String.valueOf(abc.SoLuongHoatDong(3)) + " VNĐ");
        jlb9.setText("Tổng  tiền từ thiện:    " + String.valueOf(getMoney()) + " VNĐ");

        showSupport(abc.getTop5());
        ChildTable("2022");
        CBNVTable("2022");
        NVQLTbale("2022");
        
        DeviceTable("2022");
    }
    public int getMoney(){
        ArrayList<String> list = new ArrayList<>();
        list = abc.getMoney();
        int Money = 0;
        for(int i = 0; i < list.size(); i++){
            int n = 0;
            try {
                n = Integer.parseInt(list.get(i));
                Money += n;
            } catch (Exception e) {
                
            }
            
        }
        return Money;
    }

    public void setMenu(JLabel l) {
        jlb11.setBackground(Color.white);
        jlb12.setBackground(Color.white);
        jlb13.setBackground(Color.white);
        l.setBackground(new Color(0, 255, 204));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jlb3 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jlb1 = new javax.swing.JLabel();
        jlb2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        pieChartGender = new javax.swing.JPanel();
        LineChartAge = new javax.swing.JPanel();
        barchartChild = new javax.swing.JPanel();
        ChildButton = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jlb4 = new javax.swing.JLabel();
        jlb5 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        NVQLChart = new javax.swing.JPanel();
        CBNVChart = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jlb6 = new javax.swing.JLabel();
        jlb7 = new javax.swing.JLabel();
        jlb8 = new javax.swing.JLabel();
        jlb9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtbRank = new javax.swing.JTable();
        LinePriceDevice = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jlb13 = new javax.swing.JLabel();
        jlb11 = new javax.swing.JLabel();
        jlb12 = new javax.swing.JLabel();
        jbtAdd1 = new view.JButtonCustom();
        jbtReset1 = new view.JButtonCustom();
        jtbtDel1 = new view.JButtonCustom();
        jLabel18 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jbtResest1 = new view.JButtonCustom();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(330, 70, 70, 70));
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.RIGHT);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setBackground(new java.awt.Color(153, 255, 255));
        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel16.setOpaque(true);
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 1120, 10));

        jlb3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jlb3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Close_26px.png"))); // NOI18N
        jlb3.setText("Số trẻ đã được nhận nuôi: 32");
        jPanel4.add(jlb3, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 50, 340, 40));

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel3.setText("Số trẻ được giới thiệu vào trong năm");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 460, -1, -1));

        jLabel17.setBackground(new java.awt.Color(153, 255, 255));
        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel17.setOpaque(true);
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 220, 10, 200));

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel4.setText("Chọn năm muốn thống kê");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 210, 30));

        jlb1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jlb1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/sum_24px.png"))); // NOI18N
        jlb1.setText("Tổng số trẻ hiện tại trong trung tâm:  15");
        jPanel4.add(jlb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 360, 40));

        jlb2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jlb2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/add_24px.png"))); // NOI18N
        jlb2.setText("Tổng số được giới thiệu vào trung tâm:  20");
        jPanel4.add(jlb2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, 370, 40));

        jLabel7.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel7.setText("Biểu đồ thể hiện độ tuổi trẻ");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 460, -1, -1));

        jLabel19.setBackground(new java.awt.Color(153, 255, 255));
        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel19.setOpaque(true);
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 220, 10, 200));

        pieChartGender.setLayout(new java.awt.BorderLayout());
        jPanel4.add(pieChartGender, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 310, 280));

        LineChartAge.setLayout(new java.awt.BorderLayout());
        jPanel4.add(LineChartAge, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 170, 310, 280));

        barchartChild.setLayout(new java.awt.BorderLayout());
        jPanel4.add(barchartChild, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 170, 310, 280));

        ChildButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        ChildButton.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ChildButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ChildButtonMouseClicked(evt);
            }
        });
        ChildButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChildButtonActionPerformed(evt);
            }
        });
        jPanel4.add(ChildButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 10, 90, 30));

        jLabel8.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel8.setText("Biểu đồ thể hiện giới tính trẻ");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 460, -1, -1));

        jLabel9.setBackground(new java.awt.Color(255, 51, 51));
        jLabel9.setOpaque(true);
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 520, 40, 30));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Nam");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 520, 40, 30));

        jLabel11.setBackground(new java.awt.Color(51, 51, 255));
        jLabel11.setOpaque(true);
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 520, 40, 30));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Nữ");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 520, 40, 30));

        jTabbedPane1.addTab("tab1", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlb4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jlb4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/sum_24px.png"))); // NOI18N
        jlb4.setText("Tổng CBNV:    15");
        jPanel5.add(jlb4, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 30, 250, 40));

        jlb5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jlb5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/sum_24px.png"))); // NOI18N
        jlb5.setText("Tổng NVQL:    15");
        jPanel5.add(jlb5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 250, 40));

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel5.setText("Biểu đồ trạng thái làm việc của NVQL");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 410, -1, 40));

        jLabel21.setBackground(new java.awt.Color(153, 255, 255));
        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel21.setOpaque(true);
        jPanel5.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 120, 10, 250));

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel6.setText("Biểu đồ trạng thái làm việc của CBNV");
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 410, -1, 40));

        NVQLChart.setLayout(new java.awt.BorderLayout());
        jPanel5.add(NVQLChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 380, 260));

        CBNVChart.setLayout(new java.awt.BorderLayout());
        jPanel5.add(CBNVChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 110, 370, 270));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Đang làm");
        jPanel5.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 490, 70, 30));

        jLabel15.setBackground(new java.awt.Color(51, 51, 255));
        jLabel15.setOpaque(true);
        jPanel5.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 490, 40, 30));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Nghĩ việc");
        jPanel5.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 490, 70, 30));

        jLabel23.setBackground(new java.awt.Color(255, 51, 51));
        jLabel23.setOpaque(true);
        jPanel5.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 490, 40, 30));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel24.setText("Đang làm");
        jPanel5.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 480, 70, 30));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setText("Nghĩ việc");
        jPanel5.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 480, 70, 30));

        jLabel26.setBackground(new java.awt.Color(51, 51, 255));
        jLabel26.setOpaque(true);
        jPanel5.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 480, 40, 30));

        jLabel27.setBackground(new java.awt.Color(255, 51, 51));
        jLabel27.setOpaque(true);
        jPanel5.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 480, 40, 30));

        jTabbedPane1.addTab("tab2", jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlb6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jlb6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlb6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/sum_24px.png"))); // NOI18N
        jlb6.setText("Tổng tiền mua trang thiết bị    15");
        jPanel6.add(jlb6, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 80, 400, 40));

        jlb7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jlb7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/sum_24px.png"))); // NOI18N
        jlb7.setText("Tổng hoạt động từ thiện:    15");
        jPanel6.add(jlb7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 300, 40));

        jlb8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jlb8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/sum_24px.png"))); // NOI18N
        jlb8.setText("Tổng hoạt động ngoại khóa:    15");
        jPanel6.add(jlb8, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 20, 300, 40));

        jlb9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jlb9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlb9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/sum_24px.png"))); // NOI18N
        jlb9.setText("Tổng tiền từ thiện    15");
        jPanel6.add(jlb9, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 370, 40));

        jtbRank.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jtbRank.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Thứ Hạng", "Tên Tổ Chức", "Số lần ủng hộ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtbRank.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jtbRank.setGridColor(new java.awt.Color(0, 204, 204));
        jtbRank.setOpaque(false);
        jtbRank.setRowHeight(40);
        jtbRank.setSelectionBackground(new java.awt.Color(102, 204, 255));
        jtbRank.setShowHorizontalLines(true);
        jtbRank.setShowVerticalLines(true);
        jtbRank.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbRankMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtbRank);
        if (jtbRank.getColumnModel().getColumnCount() > 0) {
            jtbRank.getColumnModel().getColumn(0).setPreferredWidth(80);
            jtbRank.getColumnModel().getColumn(0).setMaxWidth(80);
        }

        jPanel6.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 300, 620, 230));

        LinePriceDevice.setLayout(new java.awt.BorderLayout());
        jPanel6.add(LinePriceDevice, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 490, 380));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Biểu đồ giá thiết bị được nhập trong năm");
        jPanel6.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 540, 410, 30));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("TOP 5 TỔ CHỨC TỪ THIỆN THƯỜNG XUYÊN");
        jPanel6.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 230, 410, 40));

        jTabbedPane1.addTab("tab3", jPanel6);

        jPanel3.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 1240, 610));

        jlb13.setBackground(new java.awt.Color(0, 255, 204));
        jlb13.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jlb13.setOpaque(true);
        jPanel3.add(jlb13, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 0, 100, 10));

        jlb11.setBackground(new java.awt.Color(0, 255, 204));
        jlb11.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jlb11.setOpaque(true);
        jPanel3.add(jlb11, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 100, 10));

        jlb12.setBackground(new java.awt.Color(0, 255, 204));
        jlb12.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jlb12.setOpaque(true);
        jPanel3.add(jlb12, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 0, 100, 10));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1190, 640));

        jbtAdd1.setBorder(null);
        jbtAdd1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/list_view_24px.png"))); // NOI18N
        jbtAdd1.setText("Thống kê Trẻ");
        jbtAdd1.setBoderColor(new java.awt.Color(255, 255, 255));
        jbtAdd1.setColoOver(new java.awt.Color(255, 102, 51));
        jbtAdd1.setColor(new java.awt.Color(51, 255, 153));
        jbtAdd1.setColorClick(new java.awt.Color(0, 204, 255));
        jbtAdd1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jbtAdd1.setRadius(40);
        jbtAdd1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtAdd1MouseClicked(evt);
            }
        });
        jbtAdd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAdd1ActionPerformed(evt);
            }
        });
        jPanel2.add(jbtAdd1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 220, 50));

        jbtReset1.setBorder(null);
        jbtReset1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/add_24px.png"))); // NOI18N
        jbtReset1.setText("Thống kê nhân viên");
        jbtReset1.setBoderColor(new java.awt.Color(255, 255, 255));
        jbtReset1.setColoOver(new java.awt.Color(255, 102, 51));
        jbtReset1.setColor(new java.awt.Color(51, 255, 153));
        jbtReset1.setColorClick(new java.awt.Color(0, 204, 255));
        jbtReset1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jbtReset1.setRadius(40);
        jbtReset1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtReset1MouseClicked(evt);
            }
        });
        jPanel2.add(jbtReset1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, 230, 50));

        jtbtDel1.setBorder(null);
        jtbtDel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Close_26px.png"))); // NOI18N
        jtbtDel1.setText("Thống kê sơ bộ");
        jtbtDel1.setBoderColor(new java.awt.Color(255, 255, 255));
        jtbtDel1.setColoOver(new java.awt.Color(255, 102, 51));
        jtbtDel1.setColor(new java.awt.Color(51, 255, 153));
        jtbtDel1.setColorClick(new java.awt.Color(0, 204, 255));
        jtbtDel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jtbtDel1.setRadius(40);
        jtbtDel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbtDel1MouseClicked(evt);
            }
        });
        jPanel2.add(jtbtDel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 0, 210, 50));

        jLabel18.setBackground(new java.awt.Color(0, 255, 204));
        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel18.setOpaque(true);
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 20, 60, 10));

        jLabel22.setBackground(new java.awt.Color(0, 255, 204));
        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel22.setOpaque(true);
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 80, 10));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1190, 680));

        jbtResest1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/reset_24px.png"))); // NOI18N
        jbtResest1.setBoderColor(new java.awt.Color(242, 242, 242));
        jbtResest1.setColoOver(new java.awt.Color(153, 255, 204));
        jbtResest1.setColor(new java.awt.Color(242, 242, 242));
        jbtResest1.setColorClick(new java.awt.Color(102, 255, 153));
        jbtResest1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtResest1ActionPerformed(evt);
            }
        });
        jPanel1.add(jbtResest1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 50, 50));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("THỐNG KÊ SƠ BỘ QUẢN LÝ TRUNG TÂM BẢO TRỢ XÃ HỘI- TRẠI TRẺ MỒ CÔI");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 800, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 3, 1190, 720));

        setSize(new java.awt.Dimension(1191, 722));
    }// </editor-fold>//GEN-END:initComponents

    private void jbtAdd1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtAdd1MouseClicked
        jTabbedPane1.setSelectedIndex(0);
        setMenu(jlb11);
        LoadForm();
    }//GEN-LAST:event_jbtAdd1MouseClicked

    private void jbtReset1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtReset1MouseClicked
        jTabbedPane1.setSelectedIndex(1);
        setMenu(jlb12);
        LoadForm();
    }//GEN-LAST:event_jbtReset1MouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        LoadForm();
    }//GEN-LAST:event_formWindowOpened

    private void jtbtDel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbtDel1MouseClicked
        jTabbedPane1.setSelectedIndex(2);
        LoadForm();
        setMenu(jlb13);

    }//GEN-LAST:event_jtbtDel1MouseClicked

    private void jtbRankMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbRankMouseClicked
//        getDataFromTable();
    }//GEN-LAST:event_jtbRankMouseClicked

    private void jbtAdd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAdd1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtAdd1ActionPerformed

    private void ChildButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChildButtonMouseClicked

    }//GEN-LAST:event_ChildButtonMouseClicked

    private void ChildButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChildButtonActionPerformed
        ChildTable(ChildButton.getSelectedItem().toString());
    }//GEN-LAST:event_ChildButtonActionPerformed

    private void jbtResest1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtResest1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtResest1ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        LoadForm();
    }//GEN-LAST:event_formWindowActivated

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
            java.util.logging.Logger.getLogger(statistical.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(statistical.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(statistical.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(statistical.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new statistical().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CBNVChart;
    private javax.swing.JComboBox<String> ChildButton;
    private javax.swing.JPanel LineChartAge;
    private javax.swing.JPanel LinePriceDevice;
    private javax.swing.JPanel NVQLChart;
    private javax.swing.JPanel barchartChild;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private view.JButtonCustom jbtAdd1;
    private view.JButtonCustom jbtResest1;
    private view.JButtonCustom jbtReset1;
    private javax.swing.JLabel jlb1;
    private javax.swing.JLabel jlb11;
    private javax.swing.JLabel jlb12;
    private javax.swing.JLabel jlb13;
    private javax.swing.JLabel jlb2;
    private javax.swing.JLabel jlb3;
    private javax.swing.JLabel jlb4;
    private javax.swing.JLabel jlb5;
    private javax.swing.JLabel jlb6;
    private javax.swing.JLabel jlb7;
    private javax.swing.JLabel jlb8;
    private javax.swing.JLabel jlb9;
    private javax.swing.JTable jtbRank;
    private view.JButtonCustom jtbtDel1;
    private javax.swing.JPanel pieChartGender;
    // End of variables declaration//GEN-END:variables
}
