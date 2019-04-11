function save() {
    if ($('#staticEmail').val() == 'Sergey' && $('#inputPassword').val() == '1234') {
        $('.btn.btn-outline-secondary').html('<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown1" role="button" data-toggle="dropdown"\n' +
            '       aria-haspopup="true" aria-expanded="false">\n' +
            '        <img src="../images/user.png" >\n' +
            '    </a>\n' +
            '    <div class="dropdown-menu" aria-labelledby="navbarDropdown1">\n' +
            '        <a class="dropdown-item" href="index.html">Мои заказы</a>\n' +
            '        <a class="dropdown-item" href="#">Выход</a>\n' +
            '    </div>');
        $('button[id=f]').attr('id', 'newClassForAccaunt');
    }
}


