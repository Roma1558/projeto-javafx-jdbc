package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;
import model.services.DepartmentService;


public class DepartmentFormController implements Initializable {
	
	private Department entity;
	
	private DepartmentService service;

	@FXML
	private TextField txtID;
	
	@FXML
	private TextField txtNome;
	
	@FXML
	private Label labelErrorNome;
	
	@FXML
	private Button btSalvar;
	
	@FXML
	private Button btCancelar;
	
	public void setDepartment (Department entity) {
		this.entity = entity;
	}
	
	public void setDepartmentService(DepartmentService service) {
		this.service = service;
	}
	
	@FXML
	public void onBtSaveAction(ActionEvent event) {
		entity = getFormData();
		service.saveOrUpdate(entity);
		Utils.currentStage(event).close();
	}
	
	private Department getFormData() {
		Department obj = new Department();
		
		obj.setId(Utils.tryParseToInt(txtID.getText()));
		obj.setNome(txtNome.getText());
		
		return obj;
	}

	@FXML
	public void onBtCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {	
		initializeNodes();
	}

	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtID);
		Constraints.setTextFieldMaxLength(txtNome, 50);
	}

	public void updateFormData() {
		txtID.setText(String.valueOf(entity.getId()));
		txtNome.setText(entity.getNome());
	}
}