package communPackage;

import java.io.Serializable;

public class Operation implements Serializable {
    // Identifiant de version pour la sérialisation
    private static final long serialVersionUID = 1L;

    private double operand1;
    private String operator;
    private double operand2;

    public Operation(double operand1, String operator, double operand2) {
        this.operand1 = operand1;
        this.operator = operator;
        this.operand2 = operand2;
    }

    // Getters nécessaires car le serveur est dans un autre package
    public double getOperand1() { return operand1; }
    public String getOperator() { return operator; }
    public double getOperand2() { return operand2; }

    // Méthode pour effectuer le calcul demandé
    public double calculate() {
        switch (operator) {
            case "+": return operand1 + operand2;
            case "-": return operand1 - operand2;
            case "*": return operand1 * operand2;
            case "/":
                if (operand2 != 0) {
                    return operand1 / operand2;
                } else {
                    throw new ArithmeticException("Division par zéro impossible");
                }
            default:
                throw new IllegalArgumentException("Opérateur inconnu : " + operator);
        }
    }
}