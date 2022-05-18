
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.usuario;
import java.util.List;
import javax.swing.JTable;
import model.usuarioDAO;
import vista.vista_usuario;


public class Controller implements ActionListener {
    
    usuarioDAO udao = new usuarioDAO();
    usuario u = new usuario();
    vista_usuario view = new vista_usuario();
    DefaultTableModel tblmodelo = new DefaultTableModel();

    public Controller(vista_usuario v) {
        this.view = v;
        this.view.btnAgregar.addActionListener(this);
        this.view.btnEliminar.addActionListener(this);
        this.view.btnEditar.addActionListener(this);
        this.view.btnConfirmar.addActionListener(this);
        Listar(view.tbldatos);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnAgregar) {
            LimpiarTabla();
            agregar();
            Listar(view.tbldatos);                             
        }
        
        if (e.getSource() == view.btnEliminar) {
            Eliminar();
            LimpiarTabla();
            Listar(view.tbldatos);
            
        }
        
        
        if (e.getSource()== view.btnEditar){
            int fila = view.tbldatos.getSelectedRow();
           
            if (fila == -1) {
                JOptionPane.showMessageDialog(view, "Selecionar un dato");
                
            }else{
                view.txtid.setText((String) view.tbldatos.getValueAt(fila, 0));
                view.txtNombre.setText((String) view.tbldatos.getValueAt(fila, 1));
                view.txtApellido.setText((String) view.tbldatos.getValueAt(fila, 2));
                view.txtEmail.setText((String) view.tbldatos.getValueAt(fila, 3));
                view.txtTelefono.setText((String) view.tbldatos.getValueAt(fila, 4));
            }
        }  
        
        if (e.getSource() == view.btnConfirmar){
            Confirmar();
            LimpiarTabla();
            Listar(view.tbldatos);
            
        }
    }
    
    private void Confirmar(){
        u.setIdusers(view.txtid.getText());
        u.setNombre(view.txtNombre.getText());
        u.setApellido(view.txtApellido.getText());
        u.setEmail(view.txtEmail.getText());
        u.setTelefono(view.txtTelefono.getText());
        
        int r = udao.Actualizar(u);
        
        if (r == 1){
            JOptionPane.showMessageDialog(view, "Usuario Actulizado");
        }else {
           JOptionPane.showMessageDialog(view, "error");
        }
    }
    
    private void Eliminar(){
        int fila = view.tbldatos.getSelectedRow();
        
        if (fila == -1){
            JOptionPane.showMessageDialog(view, "Seleccionar dato");
            
        }else {
            int idusers= Integer.parseInt((String) view.tbldatos.getValueAt(fila,0).toString());
            udao.Eliminar(idusers);
            JOptionPane.showMessageDialog(view, "Usuario Eliminado");
        }
    }
    
    public void agregar (){
      u.setNombre(view.txtNombre.getText());
      u.setApellido(view.txtApellido.getText());
      u.setEmail(view.txtEmail.getText());
      u.setTelefono(view.txtTelefono.getText());
       
        
        int r = udao.Agregar(u);
        
        if (r == 1) {
            JOptionPane.showMessageDialog(view, "Usuario Registrado");
        }else {
            JOptionPane.showMessageDialog(view, "Usuario NO Registrado");
        }
        
        
    }
    
    private void Listar(JTable tabla){
        tblmodelo = (DefaultTableModel) tabla.getModel();
        
        List<usuario> lista = udao.listar();
        Object[] object = new Object[5];
        
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getIdusers();
            object[1] = lista.get(i).getNombre();    
            object[2] = lista.get(i).getApellido();
            object[3] = lista.get(i).getEmail();
            object[4] = lista.get(i).getTelefono();
            tblmodelo.addRow(object);
        }
        
        view.tbldatos.setModel(tblmodelo);
    }
    private void LimpiarTabla() {
        for (int i = 0; i < view.tbldatos.getRowCount(); i++) {
            tblmodelo.removeRow(i);
            i = i - 1;

        }
 } 
    
}
