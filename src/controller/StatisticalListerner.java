/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Admin
 */
public class StatisticalListerner extends ConnectDatabase {
    public DefaultPieDataset Gender(String year) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        double[] ID = new double[2]  ;
        int i = 0;
        String end = "-12-31";
        end = year + end;  
        try {
            
            String a = "select dbo.TRE.GioiTinh,COUNT(dbo.TRE.GioiTinh)  as NUMBER \n" +
                        "from QUANLYTRE , TRE \n" +
                        "where QUANLYTRE.ID_Tre = TRE.ID_Tre \n" +
                        "AND (QUANLYTRE.NgayRoiTT <= ? or QUANLYTRE.NgayRoiTT is  null) \n" +
                        "GROUP BY GioiTinh";
            java.sql.PreparedStatement b = conn.prepareStatement(a);
            b.setString(1, end);
            ResultSet c = b.executeQuery();
            while ((c.next())) {
                if(c.getString(1).compareTo("Nam") == 0)
                    ID[0] = c.getDouble(2);
                if(c.getString(1).compareTo("Nữ") == 0)
                    ID[1] = c.getDouble(2);
            }
            
        dataset.setValue( String.valueOf( Math.round( (ID[0]/ ( ID[1] + ID[0]) *100.0)/ (100.0)*100)  ) + "%"  ,ID[0] );  
        dataset.setValue( String.valueOf( Math.round( (ID[1]/ ( ID[1] + ID[0]) *100.0)/ (100.0)*100)  ) + "%" , ID[1] ); 
            
        } catch (Exception e) {
             e.printStackTrace();
        }
        
        return dataset;
    }
    public String TreOrIntroduceOrAdopt(int i){
        String sql = "";
        if(i == 1)
             sql = "select (MONTH(ql.NgayVaoTT)) AS month, COUNT(*) AS SoLuong \n" +
                    "from QUANLYTRE as QL , TRE AS T\n" +
                    "where QL.NgayVaoTT >= ? and QL.NgayVaoTT <= ? \n" +
                    "AND QL.ID_Tre = T.ID_Tre AND QL.NgayRoiTT IS  NULL  \n" +
                    "GROUP BY MONTH(ql.NgayVaoTT)" ;
        if(i == 2)
            sql = "select (MONTH(Ql.NgayGioiThieu)) AS month, COUNT(*) AS SoLuong \n" +
                    "from GIOITHIEUTRE as QL \n" +
                    "where ql.NgayGioiThieu >= ? and ql.NgayGioiThieu <= ?\n" +
                    "GROUP BY MONTH(Ql.NgayGioiThieu)";
        if(i == 3)
            sql = "select (MONTH(Ql.NgayNhan)) AS month, COUNT(*) AS SoLuong \n" +
                    "from CHITIETNHANNUOITRE as QL \n" +
                    "where ql.NgayNhan >= ? and ql.NgayNhan <= ?\n" +
                    "GROUP BY MONTH(Ql.NgayNhan)";
        return sql;
    }
    public DefaultCategoryDataset PresentChild(String year) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int i = 1;
        try {
            String first = year + "-01-01";
            String end = year + "-12-31";
            //String sql = "select (MONTH(ql.NgayVaoTT)) AS month, COUNT(*) AS SoLuong from QUANLYTRE as QL , TRE AS T where QL.NgayVaoTT >= ? and QL.NgayVaoTT <= ? AND QL.ID_Tre = T.ID_Tre AND QL.NgayRoiTT IS NOT NULL  GROUP BY MONTH(ql.NgayVaoTT)" ;
            String sql = TreOrIntroduceOrAdopt(1);
            PreparedStatement b = conn.prepareStatement(sql);
            b.setString(1, first);
            b.setString(2, end);
            ResultSet c = b.executeQuery();
            
            int dem = 0;
            while ( c.next()) {                        
                dem = c.getInt(1);
                while(dem != i){
                    dataset.setValue(0, "Amount",  String.valueOf(i));
                    i++;                   
                }                                       
                    dataset.setValue(c.getDouble(2), "Amount",  String.valueOf(i));
                    i++;                                           
            }
            dem++;
            while( dem <= 12){
                dataset.setValue(0, "Amount",  String.valueOf(dem));
                dem++;
            }                   
        } catch (Exception e) {
             //e.printStackTrace();
        }
        return  dataset;
    }
        public DefaultCategoryDataset IntroduceChild(String year) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int i = 1;
        try {
            String first = year + "-01-01";
            String end = year + "-12-31";
            //String sql = "select (MONTH(ql.NgayVaoTT)) AS month, COUNT(*) AS SoLuong from QUANLYTRE as QL , TRE AS T where QL.NgayVaoTT >= ? and QL.NgayVaoTT <= ? AND QL.ID_Tre = T.ID_Tre AND QL.NgayRoiTT IS NOT NULL  GROUP BY MONTH(ql.NgayVaoTT)" ;
            String sql = TreOrIntroduceOrAdopt(2);
            PreparedStatement b = conn.prepareStatement(sql);
            b.setString(1, first);
            b.setString(2, end);
            ResultSet c = b.executeQuery();
            
            int dem = 0;
            while ( c.next()) {                        
                dem = c.getInt(1);
                while(dem != i){
                    dataset.setValue(0, "Amount",  String.valueOf(i));
                    i++;                   
                }                                       
                    dataset.setValue(c.getDouble(2), "Amount",  String.valueOf(i));
                    i++;                                           
            }
            dem++;
            while( dem <= 12){
                dataset.setValue(0, "Amount",  String.valueOf(dem));
                dem++;
            }                   
        } catch (Exception e) {
             //e.printStackTrace();
        }
        return  dataset;
    }
        public DefaultCategoryDataset adoptChild(String year) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int i = 1;
        try {
            String first = year + "-01-01";
            String end = year + "-12-31";
            //String sql = "select (MONTH(ql.NgayVaoTT)) AS month, COUNT(*) AS SoLuong from QUANLYTRE as QL , TRE AS T where QL.NgayVaoTT >= ? and QL.NgayVaoTT <= ? AND QL.ID_Tre = T.ID_Tre AND QL.NgayRoiTT IS NOT NULL  GROUP BY MONTH(ql.NgayVaoTT)" ;
            String sql = TreOrIntroduceOrAdopt(3);
            PreparedStatement b = conn.prepareStatement(sql);
            b.setString(1, first);
            b.setString(2, end);
            ResultSet c = b.executeQuery();
            
            int dem = 0;
            while ( c.next()) {                        
                dem = c.getInt(1);
                while(dem != i){
                    dataset.setValue(0, "Amount",  String.valueOf(i));
                    i++;                   
                }                                       
                    dataset.setValue(c.getDouble(2), "Amount",  String.valueOf(i));
                    i++;                                           
            }
            dem++;
            while( dem <= 12){
                dataset.setValue(0, "Amount",  String.valueOf(dem));
                dem++;
            }                   
        } catch (Exception e) {
             //e.printStackTrace();
        }
        return  dataset;
    }
    public DefaultCategoryDataset AgeChild(String year){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        double[] age = new double[5];
        String end = "-12-31";
        try{
            String sql = "select ?-(year(T.NgaySinh)) AS year, COUNT(*) AS SoLuong \n" +
                        "from QUANLYTRE as QL , TRE AS T \n" +
                        "where QL.ID_Tre = T.ID_Tre \n" +
                        "AND QL.NgayVaoTT <= ? " +
                        "GROUP BY year((T.NgaySinh))\n" +
                        "order BY year((T.NgaySinh)) DESC ";
            PreparedStatement b = conn.prepareStatement(sql);
            end = year + end;
            b.setString(1, year);
            b.setString(2, end);
            ResultSet c = b.executeQuery();
            while(c.next()){
                int a = c.getInt(1);
                if(a <= 5)
                    age[0] = age[0] + c.getDouble(2);
                else if(a <= 10)
                        age[1] = age[1] + c.getDouble(2);
                else if(a <= 14)
                        age[2] = age[2] + c.getDouble(2);
                else if(a <= 18)
                        age[3] = age[3] + c.getDouble(2);
                else 
                        age[4] = age[4] + c.getDouble(2);

            }
            dataset.setValue(age[0], "Amount", "1-5");
            dataset.setValue(age[1], "Amount", "6-10");
            dataset.setValue(age[2], "Amount", "11-14");
            dataset.setValue(age[3], "Amount", "15-18");
           
        }catch(Exception e){
            
        }
        return dataset;
    }
    public ArrayList<String> ChildYearTable()
    {
        int i = 0;
        ArrayList<String> Year =new ArrayList<String>();
        try{
            String sql = "select (Year(ql.NgayVaoTT)) AS year, COUNT(*) AS SoLuong \n" +
                        "from QUANLYTRE as QL , TRE AS T\n" +
                        "where QL.ID_Tre = T.ID_Tre \n" +
                        "GROUP BY year(ql.NgayVaoTT)\n"+
                        "order by year(ql.NgayVaoTT) DESC";
            PreparedStatement b = conn.prepareStatement(sql);            
            ResultSet c = b.executeQuery();
            while (c.next()) {
               Year.add(c.getString(1));
               i++;               
            }
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return Year;
    }
    
    
    public String CBNV(int i)
    {
        String sql = "";
        if(i == 1)
            sql = "select count(ID_CBNV) as ngay\n" +
                "from HOPDONGLAODONG\n" +
                "where NgayKetThucLam is null\n" +
                "and NgayBatDauLam <= ?" ;
        //lam
        if(i == 2)
            sql = "select count(ID_CBNV) as ngay\n" +
                "from HOPDONGLAODONG\n" +
                "where NgayKetThucLam is not null\n" +
                "and NgayBatDauLam <= ?" ;

        //nghi
        return sql;
        
    }
    public String NVQL(int i)
    {
        String sql = "";
        if(i == 1)
            sql = "select count(*) as ngay\n" +
                "from NHANVIENQUANLY\n" +
                "where NgayNghiViec is null\n" +
                "and NgayVaoLam  <= ?" ;
        //lam
        if(i == 2)
            sql = "select count(*) as ngay\n" +
                    "from NHANVIENQUANLY\n" +
                    "where NgayNghiViec is not null\n" +
                    "and NgayVaoLam  <= ?" ;

        //nghi
        return sql;
        
    }
    
    public DefaultPieDataset QuitOrNotCBNV(String year){
        DefaultPieDataset dataset = new DefaultPieDataset();
        double[] a = new double[2];
        String end = "-12-31";
        end = year + end;  
        try{
            String sql = CBNV(1);
            PreparedStatement b = conn.prepareStatement(sql);
            b.setString(1, end);
            ResultSet c = b.executeQuery();   
            c.next();
            a[0] = c.getDouble(1);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            String sql = CBNV(2);
            PreparedStatement b = conn.prepareStatement(sql);
           b.setString(1, end);
            ResultSet c = b.executeQuery(); 
            c.next();
            a[1] = c.getDouble(1);
                                
        }catch(Exception e){
            e.printStackTrace();
        }       
        dataset.setValue( String.valueOf( Math.round( (a[0]/ ( a[1]+a[0]) *100.0)/ (100.0)*100)  ) + "%" , a[0]); 
        dataset.setValue( String.valueOf( Math.round( (a[1]/ ( a[1]+a[0]) *100.0)/ (100.0)*100)  ) + "%" , a[1]); 
        return dataset;
    }
    
    
    public DefaultPieDataset QuitOrNotNVQL(String year){
        DefaultPieDataset dataset = new DefaultPieDataset();
        double[] a = new double[2];
        String end = "-12-31";
        end = year + end;  
        try{
            String sql = NVQL(1);
            PreparedStatement b = conn.prepareStatement(sql);
            b.setString(1, end);
            ResultSet c = b.executeQuery();   
            c.next();
            a[0] = c.getDouble(1);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            String sql = NVQL(2);
            PreparedStatement b = conn.prepareStatement(sql);
           b.setString(1, end);
            ResultSet c = b.executeQuery(); 
            c.next();
            a[1] = c.getDouble(1);
                                
        }catch(Exception e){
            e.printStackTrace();
        }       
        dataset.setValue( String.valueOf( Math.round( (a[0]/ ( a[1]+a[0]) *100.0)/ (100.0)*100)  ) + "%" , a[0]); 
        dataset.setValue( String.valueOf( Math.round( (a[1]/ ( a[1]+a[0]) *100.0)/ (100.0)*100)  ) + "%" , a[1]); 
        return dataset;
    }
    public ArrayList<String> CBNVYear()
    {
        int i = 0;
        ArrayList<String> Year =new ArrayList<String>();
        try{
            String sql = "select (Year(HD.NgayBatDauLam)) AS year, COUNT(*) AS SoLuong \n" +
                        "from HOPDONGLAODONG as HD         \n" +
                        "GROUP BY year(HD.NgayBatDauLam)\n" +
                        "order by year(HD.NgayBatDauLam) asc";
            PreparedStatement b = conn.prepareStatement(sql);            
            ResultSet c = b.executeQuery();
            while (c.next()) {
               Year.add(c.getString(1));
               i++;               
            }
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return Year;
    }
    public ArrayList<String> QLNVYear()
    {
        int i = 0;
        ArrayList<String> Year =new ArrayList<String>();
        try{
            String sql = "select (Year(NVQL.NgayVaoLam)) AS year, COUNT(*) AS SoLuong \n" +
                        "from NHANVIENQUANLY as NVQL\n" +
                        "GROUP BY year(NVQL.NgayVaoLam)\n" +
                        "order by year(NVQL.NgayVaoLam) asc";
            PreparedStatement b = conn.prepareStatement(sql);            
            ResultSet c = b.executeQuery();
            while (c.next()) {
               Year.add(c.getString(1));
               i++;               
            }
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return Year;
    }
// --------------------------------------------------------

    public DefaultCategoryDataset DevicePrice(String year){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int i = 1;
        String first = "-01-01" ;
        first = year + first;
        String end = "-12-31";
        end = year + end; 
        try{
            String sql = "select (MONTH(CT.NgayNhapVao)) AS month, sum(TB.SoThietBiTot*tb.Gia)/1000 AS SoLuong\n" +
                        "from CHITIETTRANGTHIETBI as CT , TRANGTHIETBI as TB\n" +
                        "where CT.MaThietBi = tb.MaThietBi \n" +
                        "and CT.NgayNhapVao >= ? and CT.NgayNhapVao <= ? \n" +
                        "GROUP BY MONTH(ct.NgayNhapVao)";
            PreparedStatement b = conn.prepareStatement(sql);
            b.setString(1, first);
            b.setString(2, end);
            ResultSet c = b.executeQuery();
            int dem = 0;
            while ( c.next()) {                        
                dem = c.getInt(1);
                while(dem != i){
                    dataset.setValue(0, "Amount",  String.valueOf(i));
                    i++;                   
                }                                       
                    dataset.setValue(c.getDouble(2), "Amount",  String.valueOf(i));
                    i++;                                           
            }
            dem++;
            while( dem <= 12){
                dataset.setValue(0, "Amount",  String.valueOf(dem));
                dem++;
            }            
            
        }catch(Exception e){
            
        }
        return dataset;
    }
    
    
    // --------------------------------------------------------
    public int SoLuongNV(int i) {
        String sql = "";
        if (i == 1) {
            sql = "SELECT COUNT(ID_NVQL) AS SoLuong\n"
                    + "FROM NHANVIENQUANLY";
        } else {
            sql = "SELECT COUNT(ID_CBNV) AS SoLuong\n"
                    + "FROM TAIKHOANCB";
        }

        int n = 0;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                n = rs.getInt(1);
            }
//            return n;
        } catch (Exception e) {
            e.getMessage();
        }
        return n;
    }

    public int SoLuongHoatDong(int i) {
        String sql = "";
        if (i == 1) {
            sql = "select count(*) from HOATDONGTUTHIEN\n";
        } else if (i == 2) {
            sql = "select count(*) from HOATDONGNGOAIKHOA";
        } else {
            sql = "select Sum(SoThietBiTot * Gia)\n"
                    + "from TRANGTHIETBI";
        }

        int n = 0;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                n = rs.getInt(1);
            }
//            return n;
        } catch (Exception e) {
            e.getMessage();
        }
        return n;
    }

    public ArrayList<String> getMoney() {
        ArrayList<String> list = new ArrayList<>();
        String dql = "select (HienVatTuThien)\n"
                + "from HOATDONGTUTHIEN";
        try {
            PreparedStatement pre = conn.prepareStatement(dql);
            ResultSet rs = pre.executeQuery();
            while(rs.next()){
                list.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return list;
    }

    public ArrayList<String> getTop5() {
        ArrayList<String> list = new ArrayList<>();
        String sql = "SELECT TenToChucTuThien, count (TenToChucTuThien)\n"
                + "from HOATDONGTUTHIEN\n"
                + "group by TenToChucTuThien\n"
                + "order by count(TenToChucTuThien) DESC";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                String key = rs.getString(1);
                int values = rs.getInt(2);
                list.add(key + ";" + String.valueOf(values));
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return list;
    
    }
    
}
