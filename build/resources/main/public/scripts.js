$(document).ready(function () {
var s ;
  $("#setTime").submit(function(event){
    event.preventDefault();
     var x = document.getElementById("timeInput").value;
     var s = x ;
    });

    if (t < 0) {
            clearInterval(x);
            document.getElementById("demo").innerHTML = "TIME UP";
            document.getElementById("day").innerHTML ='0';
            document.getElementById("hour").innerHTML ='0';
            document.getElementById("minute").innerHTML ='0' ;
            document.getElementById("second").innerHTML = '0'; }
    }, 1000);
});
