function* fibonacci() {
    let current = 0;
    let next = 1;

    while (true) {
        yield current;
        let temp = current;
        current = next;
        next += temp;
    }
}

const fibGen = fibonacci();

console.log(fibGen.next().value); // 0
console.log(fibGen.next().value); // 1
console.log(fibGen.next().value); // 1
console.log(fibGen.next().value); // 2
console.log(fibGen.next().value); // 3
console.log(fibGen.next().value); // 5
console.log(fibGen.next().value); // 8
console.log(fibGen.next().value); // 13
