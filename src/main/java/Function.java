import lombok.Data;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

@Data
public class Function {
    private Expression function;
    private String stringFunction;

    public Function(String stringFunction, String... vars) {
        this.stringFunction = stringFunction;
        this.function = new ExpressionBuilder(stringFunction).variables(vars).build();
    }

    public double getValueOfFunction(double... values) {
        try {
            int idx = 0;
            for (String var : function.getVariableNames()) {
                this.function.setVariable(var, values[idx++]);
            }
            return this.function.evaluate();
        } catch (RuntimeException e) {
            return Double.NaN;
        }
    }

    @Override
    public String toString() {
        return stringFunction;
    }
}
