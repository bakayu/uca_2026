function fetchUsers() {
    const worker = new Worker('worker.js');

    worker.onmessage = (event) => {
        const { success, data, error } = event.data;

        if (!success) {
            console.error(`Error: ${error}`);
            worker.terminate();
            return;
        }

        data.forEach((user) => {
            const name = user.name || 'Unknown';
            const status = user.active ? 'Active' : 'Inactive';
            console.log(`${name} - ${status}`);
        });

        worker.terminate();
    };

    worker.onerror = (err) => {
        console.error('Worker Error:', err.message);
        worker.terminate();
    };

    worker.postMessage('start');
}

fetchUsers();
