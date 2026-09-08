function NewPromise(executorFunction) {
    let state = "pending";
    let value = undefined;
    const onFulfilledCallbacks = [];
    const onRejectedCallbacks = [];

    const resolve = (val) => {
        if (state !== "pending") return;
        state = "fulfilled";
        value = val;
        onFulfilledCallbacks.forEach((fn) => fn());
    };

    const reject = (reason) => {
        if (state !== "pending") return;
        state = "rejected";
        value = reason;
        onRejectedCallbacks.forEach((fn) => fn());
    };

    this.then = function (onFulfilled, onRejected) {
        const handleSuccess = typeof onFulfilled === "function" ? onFulfilled : (v) => v;
        const handleFailure = typeof onRejected === "function" ? onRejected : (err) => { throw err; };

        return new NewPromise((nextResolve, nextReject) => {
            const execute = (handler) => {
                queueMicrotask(() => {
                    try {
                        const result = handler(value);
                        if (result instanceof NewPromise) {
                            result.then(nextResolve, nextReject);
                        } else {
                            nextResolve(result);
                        }
                    } catch (err) {
                        nextReject(err);
                    }
                });
            };

            if (state === "fulfilled") {
                execute(handleSuccess);
            } else if (state === "rejected") {
                execute(handleFailure);
            } else {
                onFulfilledCallbacks.push(() => execute(handleSuccess));
                onRejectedCallbacks.push(() => execute(handleFailure));
            }
        });
    };

    this.catch = function (onRejected) {
        return this.then(null, onRejected);
    };

    try {
        executorFunction(resolve, reject);
    } catch (err) {
        reject(err);
    }
}

new NewPromise((resolve) => {
    resolve(5);
})
    .then((value) => value * 2)
    .then((value) => value * 5)
    .then((value) => {
        console.log(value);
    });
