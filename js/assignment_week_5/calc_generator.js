function* calculator(value) {
    let currentValue = value;

    while (true) {
        const action = yield currentValue;
        const num = Number(action.value);
        const isValid = !isNaN(num);

        switch (action.operation) {
            case "add":
                if (typeof action.value === "number") {
                    currentValue += action.value;
                    console.log(currentValue);
                } else {
                    console.log("Invalid input");
                }
                break;
            case "multiply":
                if (isValid) {
                    currentValue *= action.value;
                    console.log(currentValue);
                } else {
                    console.log("Invalid input");
                }
                break;
            case "divide":
                if (action.value === 0) {
                    console.log("Cannot divide by 0");
                } else if (isValid) {
                    currentValue /= action.value;
                    console.log(currentValue);
                } else {
                    console.log("Invalid input");
                }
                break;
            case "subtract":
                if (typeof action.value === "number") {
                    currentValue -= action.value;
                    console.log(currentValue);
                } else {
                    console.log("Invalid input");
                }
                break;
            default:
                console.log("Invalid input");
        }
    }
}


calc = calculator(50);
calc.next();
calc.next({ operation: "add", value: 30 });         // 80
calc.next({ operation: "multiply", value: "2" });   // 160
calc.next({ operation: "add", value: "30" });       // Invalid input
calc.next({ operation: "multiply", value: 0 });     // 0
calc.next({ operation: "divide", value: 0 });       // Cannot divide by 0
calc.next({ operation: "subtract", value: 10 })     // -10
