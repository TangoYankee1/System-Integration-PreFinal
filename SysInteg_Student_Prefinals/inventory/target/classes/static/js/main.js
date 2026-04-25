document.addEventListener('DOMContentLoaded', () => {
    // Auto-dismiss success alerts after 3.5s
    document.querySelectorAll('.alert-success').forEach(el => {
        setTimeout(() => {
            try { bootstrap.Alert.getOrCreateInstance(el).close(); } catch(e) {}
        }, 3500);
    });
});
