//import br.com.rcs.dao.ClienteSetDAO;
import br.com.rcs.dao.ClienteMapDAO;
import br.com.rcs.dao.IClienteDAO;
import br.com.rcs.domain.Cliente;

import javax.swing.*;

public class App {
    private static IClienteDAO iClienteDAO;

    public static void main(String[] args) {
        iClienteDAO = new ClienteMapDAO();

        String opcao = JOptionPane.showInputDialog(null,
                "Digite 1 para cadastro, 2 para colsultar, 3 para exclusão,4 para alteracão ou 5 para sair",
                "Cadastro", JOptionPane.INFORMATION_MESSAGE);


        while (!isOpcaoValida(opcao)) {
            if ("".equals(opcao)) {
                sair();
            } else if (isCadastro(opcao)) {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite os dados do cliente separados por vírgula, conforme o exemplo: Nome,CPF,Tel...",
                        "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                cadastrar(dados);
            } else if (isConsulta(opcao)) {
                String dados = JOptionPane.showInputDialog(null, "Digite o CPF do cliente",
                        "Consulta cliente", JOptionPane.INFORMATION_MESSAGE);
                consultar(dados);
            } else if (isExclusao(opcao)) {
                String dados = JOptionPane.showInputDialog(null, "Digite o CPF do cliente",
                        "Consulta cliente", JOptionPane.INFORMATION_MESSAGE);
                excluir(dados);
            }
        }
    }


    private static boolean isCadastro(String opcao) {
        return "1".equals(opcao);
    }

    private static void cadastrar(String dados) {
        String[] dadosSeparados = dados.split(",");
        Cliente cliente = new Cliente(dadosSeparados[0], dadosSeparados[1], dadosSeparados[2], dadosSeparados[3], dadosSeparados[4], dadosSeparados[5]);
        Boolean isCadastrado = iClienteDAO.cadastrar(cliente);
        if (isCadastrado) {
            JOptionPane.showInputDialog(null, "Cliente cadastrado.", "teste1");
        } else {
            JOptionPane.showInputDialog(null, "Cliente ja se encontra cadastrado", "teste");
        }
    }


    private static boolean isConsulta(String opcao) {
        return "2".equals(opcao);
    }

    private static void consultar(String dados) {
        Cliente cliente = iClienteDAO.consultar(Long.parseLong(dados));
        if (cliente != null) {
            JOptionPane.showMessageDialog(null, "Cliente encontrado com sucesso" + cliente.toString(),"sucesso",JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado", "ERRO", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private static boolean isExclusao(String opcao) {
        if ("3".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static void excluir(String dados) {
        iClienteDAO.excluir(Long.parseLong(dados));
        JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso");
    }

    private static void sair() {
        JOptionPane.showMessageDialog(null, "Saindo do sistema...", "Saída", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private static boolean isOpcaoSair(String opcao) {
        if ("5".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static boolean isOpcaoValida(String opcao) {
        if ("1".equals(opcao) || "2".equals(opcao)
                || "3".equals(opcao) || "4".equals(opcao) || "5".equals(opcao)) {
            return true;
        } else
            return false;
    }
}




