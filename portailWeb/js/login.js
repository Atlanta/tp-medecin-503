$('#send').click(function(){
  if ( !$('#login').val() || !$('#password').val() ) {
    $('#loginFalse').html('<h6 style="color:red">Veuillez saisir un identifiant et un mot de passe.</h6>');
  }
  else {
    $.ajax({
      method: "POST",
      url: "http://localhost:8181/login.php",
      data: {
        login: $('#login').val(),
        password: $('#password').val()
      }
    })

    .done(function(response) {
      if(response == 'false+User') {
        $('#loginFalse').html('<h6 style="color:red">Echec de connexion : Utilisateur inexistant.</h6>');
      }
      else if(response == 'false+Password') {
        $('#loginFalse').html('<h6 style="color:red">Echec de connexion : Mot de passe incorrect.</h6>');
      }
      else if(response == 'false+RequestError') {
        $('#loginFalse').html('<h6 style="color:red">Echec de connexion : Erreur de requête.</h6>');
      }
      else if(response == 'false+InternalError') {
        $('#loginFalse').html('<h6 style="color:red">Echec de connexion : Erreur interne du serveur.</h6>');
      }
      else {
        var date = new Date(), timeout = 60;
        date.setTime(date.getTime() + (timeout * 60 * 1000));
        var idClient = response.split('=');
        $.cookie("idClient", idClient[1], { expires: date });
        $(location).attr('href','gestion.php');
      }
    })
    .fail(function(jqXHR, textStatus) {
      alert("Erreur : " + textStatus);
    })
  }
});

$('#register').click(function(){
  var phoneFilter = /^[0-9-+]+$/;
  if ( !$('#registerFirstName').val() || !$('#registerLastName').val() || !$('#registerPhoneNumber').val() || !$('#registerPassword').val() ) {
    $('#registerStatut').html('<h6 style="color:red">Veuillez remplir tous les champs.</h6>');
  }
  else if ( $('#registerPhoneNumber').val().length < 9 ||  $('#registerPhoneNumber').val().length > 10 || !phoneFilter.test($('#registerPhoneNumber').val())) {
    $('#registerStatut').html('<h6 style="color:red">Numéro de téléphone incorrect.</h6>');
  }
  else {
    $.ajax({
      method: "POST",
      url: "http://localhost:8181/ajax.php",
      data: {
        request: 'register',
        firstName: latinize($('#registerFirstName').val()),
        lastName: latinize($('#registerLastName').val()),
        login: latinize($('#registerLastName').val() + $('#registerFirstName').val()),
        phoneNumber: $('#registerPhoneNumber').val(),
        password: $('#registerPassword').val()
      }
    })

    .done(function(response) {
      if(response == 'false+RequestError') {
        $('#registerStatut').html('<h6 style="color:red">Erreur : Erreur de requête.</h6>');
      }
      else if(response == 'false+InternalError') {
        $('#registerStatut').html('<h6 style="color:red">Erreur : Erreur interne du serveur.</h6>');
      }
      else {
        $('#registerStatut').html('<h6 style="color:green">Succès ! Votre login est : ' + response + '</h6>');
      }
    })
    .fail(function(jqXHR, textStatus) {
      alert("Erreur : " + textStatus);
    })
  }
});
