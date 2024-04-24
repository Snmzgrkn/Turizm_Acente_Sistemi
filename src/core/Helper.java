package core;

import javax.swing.*;
import java.awt.*;

public class Helper {
    public static void setTheme(){
        for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
            if("Nimbus".equals(info.getName())){
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static boolean isFieldEmpty(JTextField field){
        return field.getText().trim().isEmpty();
    }

    public static void showMessage(String str){
        optionPaneTR();
        String msg;
        String title = switch (str) {
            case "fill" -> {
                msg = "Lütfen tüm alanları doldurunuz !";
                yield "Hata!";
            }
            case "done" -> {
                msg = "İşlem Başarılı !";
                yield "Sonuç";
            }
            case "notFound" -> {
                msg = "Kayıt Bulunamadı";
                yield "Hata";
            }
            case "error" -> {
                msg = "Hatalı İşlem Yaptınız !";
                yield "Hata";
            }
            default -> {
                msg = str;
                yield "Mesaj";
            }
        };

        JOptionPane.showMessageDialog(null,msg,title,JOptionPane.INFORMATION_MESSAGE);
    }

    public  static boolean isFieldListEmpty(JTextField[] fieldList){
        for(JTextField field : fieldList){
            if(isFieldEmpty(field)){
                return true;
            }
        }
        return false;
        
    }


    public static int getLocationPoint(String type,Dimension size){
        //Açılan pencereler için hazır yazmış olduğum kod. Otomatik olarak ortada açılıyor.
        return switch (type) {
            case "x" -> (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
            case "y" -> (Toolkit.getDefaultToolkit().getScreenSize().height - size.width) / 2;
            default -> 0;
        };

    }

    public static boolean confirm(String str){
        optionPaneTR();
        String msg;
        if(str.equals("sure")){
            msg = "Bu işlemi yapmak istediğine emin misin?";
        }else {
        msg = str;
        }
        return JOptionPane.showConfirmDialog(null,msg,"Eminmisin ?",JOptionPane.YES_NO_OPTION) == 0;
    }

    public static void optionPaneTR(){
        UIManager.put("OptionPane.okButtonText","Tamam");
        UIManager.put("OptionPane.yesButtonText","Evet");
        UIManager.put("OptionPane.noButtonText","Hayır");

    }
}
