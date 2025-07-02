/**
 * Custom JavaScript for Sistema Empresarial
 * Enhanced functionality and user experience
 */

// Document Ready
document.addEventListener('DOMContentLoaded', function() {
    initializeApp();
});

// Initialize Application
function initializeApp() {
    initializeTooltips();
    initializePopovers();
    initializeDataTables();
    initializeFormValidation();
    initializeSearchFilters();
    initializeAutoRefresh();
    initializeAnimations();
    updateDateTime();
}

// Initialize Bootstrap Tooltips
function initializeTooltips() {
    const tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl);
    });
}

// Initialize Bootstrap Popovers
function initializePopovers() {
    const popoverTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'));
    popoverTriggerList.map(function (popoverTriggerEl) {
        return new bootstrap.Popover(popoverTriggerEl);
    });
}

// Enhanced Data Tables
function initializeDataTables() {
    const tables = document.querySelectorAll('.data-table');
    tables.forEach(table => {
        // Add search functionality
        addTableSearch(table);
        // Add sorting functionality
        addTableSorting(table);
        // Add row highlighting
        addTableRowHighlighting(table);
    });
}

// Add table search functionality
function addTableSearch(table) {
    const searchInput = table.parentNode.querySelector('.table-search');
    if (searchInput) {
        searchInput.addEventListener('input', function() {
            filterTable(table, this.value);
        });
    }
}

// Filter table rows
function filterTable(table, searchTerm) {
    const rows = table.querySelectorAll('tbody tr');
    const term = searchTerm.toLowerCase();
    
    rows.forEach(row => {
        const text = row.textContent.toLowerCase();
        row.style.display = text.includes(term) ? '' : 'none';
    });
    
    updateTableRowCount(table);
}

// Add table sorting
function addTableSorting(table) {
    const headers = table.querySelectorAll('th[data-sortable]');
    headers.forEach(header => {
        header.style.cursor = 'pointer';
        header.addEventListener('click', function() {
            sortTable(table, this);
        });
    });
}

// Sort table by column
function sortTable(table, header) {
    const tbody = table.querySelector('tbody');
    const rows = Array.from(tbody.querySelectorAll('tr'));
    const columnIndex = Array.from(header.parentNode.children).indexOf(header);
    const isAscending = header.classList.contains('sort-asc');
    
    // Clear previous sort indicators
    header.parentNode.querySelectorAll('th').forEach(th => {
        th.classList.remove('sort-asc', 'sort-desc');
    });
    
    // Sort rows
    rows.sort((a, b) => {
        const aText = a.children[columnIndex].textContent.trim();
        const bText = b.children[columnIndex].textContent.trim();
        
        // Check if numeric
        const aNum = parseFloat(aText.replace(/[$,]/g, ''));
        const bNum = parseFloat(bText.replace(/[$,]/g, ''));
        
        if (!isNaN(aNum) && !isNaN(bNum)) {
            return isAscending ? bNum - aNum : aNum - bNum;
        } else {
            return isAscending ? bText.localeCompare(aText) : aText.localeCompare(bText);
        }
    });
    
    // Update sort indicator
    header.classList.add(isAscending ? 'sort-desc' : 'sort-asc');
    
    // Reorder rows
    rows.forEach(row => tbody.appendChild(row));
}

// Add table row highlighting
function addTableRowHighlighting(table) {
    const rows = table.querySelectorAll('tbody tr');
    rows.forEach(row => {
        row.addEventListener('mouseenter', function() {
            this.classList.add('table-active');
        });
        row.addEventListener('mouseleave', function() {
            this.classList.remove('table-active');
        });
    });
}

// Update table row count
function updateTableRowCount(table) {
    const visibleRows = table.querySelectorAll('tbody tr[style=""]').length;
    const totalRows = table.querySelectorAll('tbody tr').length;
    const counter = table.parentNode.querySelector('.row-count');
    
    if (counter) {
        counter.textContent = `Mostrando ${visibleRows} de ${totalRows} registros`;
    }
}

// Enhanced Form Validation
function initializeFormValidation() {
    const forms = document.querySelectorAll('.needs-validation');
    
    forms.forEach(form => {
        form.addEventListener('submit', function(event) {
            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
                showValidationErrors(form);
            }
            form.classList.add('was-validated');
        });
        
        // Real-time validation
        const inputs = form.querySelectorAll('input, select, textarea');
        inputs.forEach(input => {
            input.addEventListener('blur', function() {
                validateField(this);
            });
        });
    });
}

// Validate individual field
function validateField(field) {
    const isValid = field.checkValidity();
    const feedback = field.parentNode.querySelector('.invalid-feedback');
    
    if (!isValid && feedback) {
        feedback.style.display = 'block';
        field.classList.add('is-invalid');
    } else {
        if (feedback) feedback.style.display = 'none';
        field.classList.remove('is-invalid');
        field.classList.add('is-valid');
    }
}

// Show validation errors
function showValidationErrors(form) {
    const invalidFields = form.querySelectorAll(':invalid');
    if (invalidFields.length > 0) {
        invalidFields[0].focus();
        showNotification('Por favor corrija los errores en el formulario', 'error');
    }
}

// Initialize Search Filters
function initializeSearchFilters() {
    const searchInputs = document.querySelectorAll('.search-filter');
    
    searchInputs.forEach(input => {
        let timeout;
        input.addEventListener('input', function() {
            clearTimeout(timeout);
            timeout = setTimeout(() => {
                performSearch(this.value);
            }, 300);
        });
    });
}

// Perform search with debouncing
function performSearch(query) {
    const searchResults = document.querySelector('.search-results');
    if (searchResults) {
        searchResults.innerHTML = '<div class="spinner-border" role="status"></div>';
        
        // Simulate API call
        setTimeout(() => {
            updateSearchResults(query);
        }, 500);
    }
}

// Update search results
function updateSearchResults(query) {
    const searchResults = document.querySelector('.search-results');
    if (searchResults) {
        if (query.length === 0) {
            searchResults.innerHTML = '<p class="text-muted">Ingrese un término de búsqueda</p>';
        } else {
            searchResults.innerHTML = `<p>Resultados para: "<strong>${query}</strong>"</p>`;
        }
    }
}

// Auto-refresh functionality
function initializeAutoRefresh() {
    const refreshElements = document.querySelectorAll('[data-auto-refresh]');
    
    refreshElements.forEach(element => {
        const interval = element.dataset.autoRefresh || 30000; // Default 30 seconds
        setInterval(() => {
            refreshElement(element);
        }, parseInt(interval));
    });
}

// Refresh element content
function refreshElement(element) {
    element.classList.add('loading');
    
    // Simulate refresh
    setTimeout(() => {
        element.classList.remove('loading');
        console.log('Element refreshed:', element);
    }, 1000);
}

// Initialize animations
function initializeAnimations() {
    // Fade in elements on scroll
    const observerOptions = {
        threshold: 0.1,
        rootMargin: '0px 0px -50px 0px'
    };
    
    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.classList.add('fade-in');
                observer.unobserve(entry.target);
            }
        });
    }, observerOptions);
    
    const animatedElements = document.querySelectorAll('.animate-on-scroll');
    animatedElements.forEach(el => observer.observe(el));
}

// Update date and time
function updateDateTime() {
    const timeElements = document.querySelectorAll('.current-time');
    
    function updateTime() {
        const now = new Date();
        const timeString = now.toLocaleTimeString('es-ES', {
            hour: '2-digit',
            minute: '2-digit',
            second: '2-digit'
        });
        
        timeElements.forEach(element => {
            element.textContent = timeString;
        });
    }
    
    updateTime();
    setInterval(updateTime, 1000);
}

// Notification system
function showNotification(message, type = 'info', duration = 5000) {
    const notification = document.createElement('div');
    notification.className = `alert alert-${type} alert-dismissible fade show position-fixed`;
    notification.style.cssText = 'top: 20px; right: 20px; z-index: 9999; min-width: 300px;';
    
    notification.innerHTML = `
        ${getIconForType(type)}
        ${message}
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    `;
    
    document.body.appendChild(notification);
    
    // Auto dismiss
    setTimeout(() => {
        if (notification.parentNode) {
            notification.remove();
        }
    }, duration);
}

// Get icon for notification type
function getIconForType(type) {
    const icons = {
        success: '<i class="fas fa-check-circle me-2"></i>',
        error: '<i class="fas fa-exclamation-triangle me-2"></i>',
        warning: '<i class="fas fa-exclamation-circle me-2"></i>',
        info: '<i class="fas fa-info-circle me-2"></i>'
    };
    return icons[type] || icons.info;
}

// Confirm dialogs
function confirmAction(message, callback) {
    if (confirm(message)) {
        callback();
    }
}

// Enhanced confirm dialog
function showConfirmDialog(title, message, onConfirm, onCancel) {
    const modal = document.createElement('div');
    modal.className = 'modal fade';
    modal.innerHTML = `
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">${title}</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    <p>${message}</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <button type="button" class="btn btn-primary confirm-btn">Confirmar</button>
                </div>
            </div>
        </div>
    `;
    
    document.body.appendChild(modal);
    
    const bootstrapModal = new bootstrap.Modal(modal);
    
    modal.querySelector('.confirm-btn').addEventListener('click', () => {
        bootstrapModal.hide();
        if (onConfirm) onConfirm();
    });
    
    modal.addEventListener('hidden.bs.modal', () => {
        modal.remove();
        if (onCancel) onCancel();
    });
    
    bootstrapModal.show();
}

// Loading overlay
function showLoadingOverlay(message = 'Cargando...') {
    const overlay = document.createElement('div');
    overlay.id = 'loading-overlay';
    overlay.className = 'position-fixed top-0 start-0 w-100 h-100 d-flex align-items-center justify-content-center';
    overlay.style.cssText = 'background: rgba(255,255,255,0.9); z-index: 9999;';
    
    overlay.innerHTML = `
        <div class="text-center">
            <div class="spinner-border text-primary mb-3" role="status">
                <span class="visually-hidden">Loading...</span>
            </div>
            <div>${message}</div>
        </div>
    `;
    
    document.body.appendChild(overlay);
}

function hideLoadingOverlay() {
    const overlay = document.getElementById('loading-overlay');
    if (overlay) {
        overlay.remove();
    }
}

// Enhanced AJAX functionality
function makeAjaxRequest(url, options = {}) {
    const defaultOptions = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        }
    };
    
    const finalOptions = { ...defaultOptions, ...options };
    
    showLoadingOverlay();
    
    return fetch(url, finalOptions)
        .then(response => {
            hideLoadingOverlay();
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .catch(error => {
            hideLoadingOverlay();
            showNotification('Error en la solicitud: ' + error.message, 'error');
            throw error;
        });
}

// Export functions for global use
window.SistemaEmpresarial = {
    showNotification,
    confirmAction,
    showConfirmDialog,
    showLoadingOverlay,
    hideLoadingOverlay,
    makeAjaxRequest,
    filterTable,
    sortTable
};
