
public class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0;
        int tank = 0;
        int start = 0;

        for (int i = 0; i < gas.length; i++) {
            int balance = gas[i] - cost[i];
            totalGas += balance;
            tank += balance;

            if (tank < 0) {
                start = i + 1;
                tank = 0;
            }
        }

        return totalGas >= 0 ? start : -1;
    }
}
