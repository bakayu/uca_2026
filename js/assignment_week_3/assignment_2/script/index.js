function fetchUsers() {
    const container = document.getElementById('users');
    const worker = new Worker('worker.js');

    worker.onmessage = (event) => {
        const { success, data, error } = event.data;

        if (!success) {
            container.textContent = error === 'No users found' ? 'No users found' : `Error: ${error}`;
            worker.terminate();
            return;
        }

        const fragment = document.createDocumentFragment();
        data.forEach((user) => {
            const div = document.createElement('div');
            const name = user.name || 'Unknown';
            const status = user.active ? 'Active' : 'Inactive';
            div.textContent = `${name} - ${status}`;
            fragment.appendChild(div);
        });

        container.appendChild(fragment);
        worker.terminate();
    };

    worker.onerror = (err) => {
        container.textContent = 'Failed to load users.';
        console.error('Worker Error:', err.message);
        worker.terminate();
    };

    worker.postMessage('start');
}

document.addEventListener('DOMContentLoaded', fetchUsers);
