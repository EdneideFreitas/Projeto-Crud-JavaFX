package application;

import javax.swing.JOptionPane;

import br.com.projetoCrud.Dao.PessoaDao;
import br.com.projetoCrud.domain.Pessoa;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Main extends Application {

	private BorderPane borderPane;
	private HBox hbox;
	private VBox vboxFormulario;
	private HBox hboxBotaoCadastro;
	private VBox vboxTable;

	private Label labelTitulo;
	private Label nome;
	private Label email;
	private Label sobreNome;

	private TextField nomeTextField;
	private TextField emailTextField;
	private TextField sobreNomeTextField;

	private Button buttonSalvar;
	private Button buttonCancelar;
	private Button buttonExcluir;
	private Button buttonEditar;

	private TableView<Pessoa> tableView;
	private TableColumn<Pessoa, String> nomeColumn;
	private TableColumn<Pessoa, String> emailColumn;
	private TableColumn<Pessoa, String> sobreNomeColumn;

	ObservableList<Pessoa> data;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void start(Stage primaryStage) {

		// CARREGA MINHA LISTA
		data = new PessoaDao().listar();
		tableView = new TableView<Pessoa>(data);
		borderPane = new BorderPane();

		hbox = new HBox();
		hbox.setId("topoID");

		labelTitulo = new Label("PROJETO CRUD - CADASTRO DE PESSOAS");
		hbox.getChildren().add(labelTitulo);

		/****************************************************************************
		 * FORMULARIO DE CADASTRO
		 ****************************************************************************/

		vboxFormulario = new VBox();
		vboxFormulario.setId("vboxFormulario");

		nome = new Label("Nome");
		nomeTextField = new TextField();

		email = new Label("E-mail");
		emailTextField = new TextField();

		sobreNome = new Label("Sobre Nome");
		sobreNomeTextField = new TextField();

		vboxFormulario.getChildren().addAll(nome, nomeTextField,  sobreNome, sobreNomeTextField, email, emailTextField);

		/****************************************************************************
		 * BOTAO DE SALVAR E CADASTRAR
		 ****************************************************************************/
		Group groupBotao = new Group();
		
		hboxBotaoCadastro = new HBox();
		hboxBotaoCadastro.setId("hboxBotaoCadastroID");

		buttonSalvar = new Button("Salvar");

		buttonCancelar = new Button("Cancelar");
		buttonCancelar.setId("buttonCancelarID");
		
		
		
		HBox hboxButtonEdit = new HBox();
		hboxButtonEdit.setId("hboxButtonEditID");
		
		//BOTAO DE EDITAR E EXCLUSAO
		buttonExcluir = new Button("Excluir");
		buttonExcluir.setId("ButtonExcluirID");
		
		buttonEditar = new Button("Editar");
		buttonEditar.setId("buttonEditarID");
		
		hboxButtonEdit.getChildren().addAll(buttonExcluir, buttonEditar);
		
		// EVENTOS DE CADASTRAR PESSOAS
		buttonSalvar.setOnAction(e -> adicionarPessoa());

		// EVENTO EXCLUIR BOTAO
		buttonExcluir.setOnAction(e -> deleteButton());

		hboxBotaoCadastro.getChildren().addAll(buttonSalvar, buttonCancelar);
		
		groupBotao.getChildren().addAll(hboxBotaoCadastro, hboxButtonEdit);

		/****************************************************************************
		 * TABLEA DE PESSOAS
		 ****************************************************************************/

		vboxTable = new VBox();
		vboxTable.setId("tabelaPessoaID");

		nomeColumn = new TableColumn<Pessoa, String>("Nome");
		nomeColumn.setMinWidth(180);
		nomeColumn.setCellValueFactory(new PropertyValueFactory("nome"));

		emailColumn = new TableColumn<Pessoa, String>("E-mail");
		emailColumn.setMinWidth(180);
		emailColumn.setCellValueFactory(new PropertyValueFactory("email"));

		sobreNomeColumn = new TableColumn<Pessoa, String>("SobreNome");
		sobreNomeColumn.setMinWidth(180);
		sobreNomeColumn.setCellValueFactory(new PropertyValueFactory("sobreNome"));
		
		

		tableView.getColumns().addAll(nomeColumn, emailColumn, sobreNomeColumn);
		vboxTable.getChildren().add(tableView);

		/****************************************************************************
		 * ADD COMPONETES AO BORDER PANE
		 ****************************************************************************/

		borderPane.setTop(hbox);
		borderPane.setLeft(vboxFormulario);
		borderPane.setCenter(vboxTable);
		borderPane.setBottom(groupBotao);
		

		Scene scene = new Scene(borderPane, 930, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	// ADICIONAR PESSOAS A TABELA
	public void adicionarPessoa() {

		Pessoa pessoa = new Pessoa();
		pessoa.setNome(nomeTextField.getText());
		pessoa.setEmail(emailTextField.getText());
		pessoa.setSobreNome(sobreNomeTextField.getText());

		PessoaDao dao = new PessoaDao();
		dao.adiciona(pessoa);

		data.add(pessoa);

		// LIMPA OS CAMPOS
		limpaCampo();
	}

	// DELETA AS PESSOAS
	public void deleteButton() {
		//VERIFICA SE A TABELA ESTA VAZIA
		if(tableView.getSelectionModel().isEmpty()){
			JOptionPane.showMessageDialog(null, "OPS! a Tabela esta vazia.","Alerta", JOptionPane.WARNING_MESSAGE);
		}else{
			Pessoa pessoa = new Pessoa();
	
			// SETA O VALOR DO CAMPO NOME
			pessoa.setNome(tableView.getSelectionModel().getSelectedItem().getNome());
	
			PessoaDao dao = new PessoaDao();
			dao.excluir(pessoa);
	
			// REMOVE DA LISTA
			data.remove(tableView.getSelectionModel().getSelectedItem());
			JOptionPane.showMessageDialog(null, "Dados removido com sucesso :-)","Alerta", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	

	// LIMPA OS CAMPOS
	public void limpaCampo() {
		nomeTextField.clear();
		emailTextField.clear();
		sobreNomeTextField.clear();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
