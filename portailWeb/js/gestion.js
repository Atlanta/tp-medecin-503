function chargerRdv() {
  $.ajax({
    method: "POST",
    url: "http://localhost:8181/ajax.php",
    data: {
      request: 'clientRdv',
      idClient: $.cookie('idClient')
    }
  })

  .done(function(response) {
      $('#rdv').html(response);
  })
  .fail(function(jqXHR, textStatus) {
    alert("Erreur : " + textStatus);
  });
}

$(function() {
  //$( "#datepicker" ).datepicker( $.datepicker.regional[ "fr" ] );
	$("#datepicker").datepicker({ minDate: 0, maxDate: "+6M", dateFormat: "dd/mm/yy" });
  $.ajax({
    method: "POST",
    url: "http://localhost:8181/ajax.php",
    data: {
      request: 'chargerMedecins'
    }
  })

  .done(function(response) {
      $('#medecin').html(response);
  })
  .fail(function(jqXHR, textStatus) {
    alert("Erreur : " + textStatus);
  });
  $.ajax({
    method: "POST",
    url: "http://localhost:8181/ajax.php",
    data: {
      request: 'clientName',
      idClient: $.cookie('idClient')
    }
  })

  .done(function(response) {
      $('h1').html(response);
  })
  .fail(function(jqXHR, textStatus) {
    alert("Erreur : " + textStatus);
  });
  chargerRdv();
});

$('#add').click(function(){
  var currentDate = $('#datepicker').datepicker('getDate');
  currentDate.setHours(parseInt($('#heure').prop('selectedIndex')) + 8);
  currentDate.setMinutes(parseInt($('#minutes').prop('selectedIndex')) * 15);
  $.ajax({
    method: "POST",
    url: "http://localhost:8181/ajax.php",
    data: {
      request: 'addRdv',
      idClient: $.cookie('idClient'),
      idMedecin: $('#medecin').prop('selectedIndex'),
      annee: currentDate.getFullYear(),
      mois: currentDate.getMonth(),
      jour: currentDate.getDate(),
      heure: currentDate.getHours(),
      minutes: currentDate.getMinutes()
    }
  })

  .done(function(response) {
    if(response == 'false') {
      alert("Créneau déjà pris !");
    }
    else {
      chargerRdv();
    }
  })
  .fail(function(jqXHR, textStatus) {
    alert("Erreur : " + textStatus);
  })
});

$('#disconnect').click(function(){
  $.removeCookie("idClient");
  $(location).attr('href','index.php');
})
