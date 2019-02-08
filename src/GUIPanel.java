import javax.swing.*;
import java.awt.*;

public class GUIPanel extends JTabbedPane {
    private JTabbedPane tabPanel;
    private Component panelDiario;
    private Component panelGeneral;
    private JTable tableLavados;
    private JLabel lMatricula =   new JLabel("Matricula:  ");
    private JLabel lMarca =       new JLabel("Marca:          ");
    private JLabel lModelo =      new JLabel("Modelo:      ");
    private JLabel lHora =        new JLabel("Hora:            ");
    private JLabel lFecha =       new JLabel("Fecha:         ");
    private JLabel lTam =         new JLabel("Tamano:       ");
    private JLabel lPrecio =      new JLabel("Precio:        ");
    private JLabel lTelefono =    new JLabel("Telefono:      ");
    private JLabel lPropietario = new JLabel("Propietario:");
    private JLabel lTrabajador =  new JLabel("Trabajador:  ");

    private JTextField tMatricula = new JTextField();
    private JTextField tMarca = new JTextField();
    private JTextField tModelo = new JTextField();
    private JTextField tHora = new JTextField();
    private JTextField tFecha = new JTextField();
    private JTextField tTam = new JTextField();
    private JTextField tPrecio = new JTextField();
    private JTextField tTelefono = new JTextField();
    private JTextField tPropietario = new JTextField();
    private JTextField tTrabajador = new JTextField();


    public GUIPanel() {
        panelDiario = createPanelDiario();
        this.addTab("Diario", panelDiario);

        panelGeneral = createPanelGeneral();
        this.addTab("General", panelGeneral);
    }

    private Component createPanelDiario() {

        JPanel panelBorder = new JPanel();
        panelBorder.setLayout(new BorderLayout());

        Object rowData[][] = { { "1" },
                { "1" } };
        Object columnNames[] = { "Column One"};
        tableLavados = new JTable(rowData, columnNames);

        panelBorder.add(tableLavados, BorderLayout.CENTER);

        panelBorder.add(createSouthPanel(), BorderLayout.SOUTH);

        return new JScrollPane(panelBorder);
    }

    private JPanel create2ElementPanel(Component c1, Component c2) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(c1, BorderLayout.WEST);
        panel.add(c2, BorderLayout.CENTER);
        return panel;
    }

    private Component createSouthPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5,2,5,5));

        panel.add(create2ElementPanel(lMatricula, tMatricula));
        panel.add(create2ElementPanel(lMarca, tMarca));
        panel.add(create2ElementPanel(lModelo, tModelo));
        panel.add(create2ElementPanel(lHora, tHora));
        panel.add(create2ElementPanel(lFecha, tFecha));
        panel.add(create2ElementPanel(lTam, tTam));
        panel.add(create2ElementPanel(lPrecio, tPrecio));
        panel.add(create2ElementPanel(lTelefono, tTelefono));
        panel.add(create2ElementPanel(lPropietario, tPropietario));
        panel.add(create2ElementPanel(lTrabajador, tTrabajador));

        return panel;
    }

    private Component createPanelGeneral() {
        return null;
    }
}
