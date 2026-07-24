self.onmessage = async () => {
    try {
        const response = await fetch('https://dummyapi.com/users');

        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const users = await response.json();

        if (!Array.isArray(users) || users.length === 0) {
            throw new Error('No users found');
        }

        self.postMessage({ success: true, data: users });
    } catch (error) {
        self.postMessage({ success: false, error: error.message });
    }
};
