package application.controllerClass;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;

import com.jfoenix.controls.JFXButton;

import application.dataClass.Db;
import application.frameClass.CustomerHomepageFrame;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SignInController {

	@FXML
	private Label lbPsw;

	@FXML
	private RadioButton raCus;

	@FXML
	private RadioButton raBus;

	@FXML
	private Label lbID;

	@FXML
	private Label labError;

	@FXML
	private JFXButton btRegister;

	@FXML
	private JFXButton btSignIn;

	@FXML
	private TextField txID;

	@FXML
	private PasswordField txPsw;

	@FXML
	private AnchorPane pane;

	public void selectRaCus() {
		raBus.setSelected(false);
		raCus.setSelected(true);

	}

	public void selectRaBus() {
		raCus.setSelected(false);
		raBus.setSelected(true);

	}

	public void btSign() {
		String name = txID.getText();
		String pwd = txPsw.getText();
		String[] para = new String[2];
		System.out.println(name + " " + pwd);
		para[0] = name;
		para[1] = pwd;
		Db db = new Db();
		Object[] rs = null;
		if (raBus.isSelected()) {
			String sql = "Select count(*) from business where UserName =? and Password=?";
			QueryRunner qr = new QueryRunner();
			try {
				rs = qr.query(db.getConnection(), sql, para, new ArrayHandler());

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			String sql = "select count(*) from business where UserName =? and Password=?";
			QueryRunner qr = new QueryRunner();

			try {
				rs = qr.query(db.getConnection(), sql, para, new ArrayHandler());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		// object对象 转成String 用tostring
		int a = Integer.parseInt(rs[0].toString());
		if (a == 1) {
			System.out.println("chenggong");
			new CustomerHomepageFrame();
			Stage stage = (Stage) btSignIn.getScene().getWindow();// 获取到stage来关闭
			stage.close();
			labError.setVisible(false);
		} else {
			labError.setVisible(true);
			System.out.println("shibai");
		}

	}
}
