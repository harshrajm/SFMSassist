$( document ).ready(function() {

    $('#title').typeIt({
              speed: 90,
             autoStart: false
        }).tiType('Team Management')
            .tiPause(1000)
            .tiDelete()
            .tiType('Tickets Tracker')
            .tiPause(1000)
            .tiDelete()
            .tiType('Issues Repository')
            .tiPause(1000)
            .tiDelete()
            .tiPause(1000)
            .tiType('<Strong>SFMS ASSIST.</Strong>');

});

