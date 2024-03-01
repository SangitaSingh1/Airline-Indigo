document.addEventListener('DOMContentLoaded', function() {
    // Set the default section to display
    showSection('location');
});

function showSection(sectionId) {
    // Hide all sections
    var sections = document.getElementsByClassName('section');
    for (var i = 0; i < sections.length; i++) {
        sections[i].classList.remove('active');
    }

    // Deactivate all tabs
    var tabBtns = document.getElementsByClassName('tab-btn');
    for (var i = 0; i < tabBtns.length; i++) {
        tabBtns[i].classList.remove('active');
    }

    // Show the selected section
    document.getElementById(sectionId + 'Section').classList.add('active');

    // Activate the corresponding tab
    var activeTab = document.querySelector('.tab-btn[data-section="' + sectionId + '"]');
    if (activeTab) {
        activeTab.classList.add('active');
    }
}
