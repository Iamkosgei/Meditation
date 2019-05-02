function start_timer() {
    var time = (document.getElementById("select-time").value);
    // var time = (document.getElementById("select-time").value)*60;
    var initialOffset = '440';
    var i = 1

    $('.circle_animation').css('stroke-dashoffset', initialOffset-(1*(initialOffset/time)));

    var interval = setInterval(function() {
        $('h2').text(i);
        if (i == time) {
            clearInterval(interval);
            window.location.href = "/time/"+time;
            return;

        }
        $('.circle_animation').css('stroke-dashoffset', initialOffset-((i+1)*(initialOffset/time)));
        i++;
    }, 1000);

    var audio = new Audio('/ping.mp3');
    audio.play();
    $("#time-form").hide();
    $("#btn-set-timer").hide();


}




