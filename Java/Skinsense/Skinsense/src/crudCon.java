import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class crudCon {

    @FXML 
    private TableView<ProductTask> AllTable;
    @FXML
    private TableColumn<ProductTask, String> nameCol, brandCol, categoriCol;
    @FXML
    private TableColumn<ProductTask, Double> priceCol;
    @FXML
    private TableColumn<ProductTask, Integer> stokCol;
    @FXML
    private TextField namaProdField, brandProdField, kategoriField, hargaField, jumlahField;
    @FXML
    private ObservableList<ProductTask> taskList;

    public void initialize() {
        taskList = FXCollections.observableArrayList(TaskFileManager.loadTasks());
        AllTable.setItems(taskList);

        nameCol.setCellValueFactory(new PropertyValueFactory<>("namaProd"));
        brandCol.setCellValueFactory(new PropertyValueFactory<>("brandProd"));
        categoriCol.setCellValueFactory(new PropertyValueFactory<>("kategoriProd"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        stokCol.setCellValueFactory(new PropertyValueFactory<>("stok"));
    }

    public void saveTask() {
        try {
            if (namaProdField.getText().isEmpty() || brandProdField.getText().isEmpty() || 
                kategoriField.getText().isEmpty() || hargaField.getText().isEmpty() || 
                jumlahField.getText().isEmpty()) {
                throw new MissingFieldException("Data anda kosong, harap masukkan data anda !");
            }

            ProductTask task = new ProductTask(
                namaProdField.getText(), 
                brandProdField.getText(), 
                kategoriField.getText(), 
                Double.parseDouble(hargaField.getText()), 
                Integer.parseInt(jumlahField.getText()));
            
            taskList.add(task);
            TaskFileManager.saveTasks(new ArrayList<>(taskList));

        } catch (MissingFieldException e) {
            showError(e.getMessage());
        } catch (NumberFormatException e) {
            showError("Harga dan jumlah harus berupa angka.");
        }
    }
    @FXML
    private void clearData(){
        clearData();
        namaProdField.clear();
        brandProdField.clear();
        kategoriField.clear();
        hargaField.clear();
        jumlahField.clear();
    }
    @FXML
    private void deleteData(){
        try {
            ProductTask selected = AllTable.getSelectionModel().getSelectedItem();
            if (selected == null) {
                throw new TaskNotSelectedException("Tolong pilih data yang akan di hapus");
            }
            taskList.remove(selected);
            TaskFileManager.saveTasks(new ArrayList<>());
        } catch (TaskNotSelectedException e) {
            showError(e.getMessage());
        }

    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.showAndWait();
    }

}
class MissingFieldException extends Exception {
    public MissingFieldException (String message) {
        super(message);}
}
class TaskNotSelectedException extends Exception {
    public TaskNotSelectedException (String message){
        super(message);
    }
    
}

