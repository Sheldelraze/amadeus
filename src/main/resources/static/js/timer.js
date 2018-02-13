function getTimeRemaining(startTime,endTime){
    var current = new Date();
    var start = Date.parse(startTime);
    var now = Date.parse(current);
    var end = Date.parse(endTime);
    var t = end - now;
    var all = end - start;
    var seconds = Math.floor( (t/1000) % 60 );
    var minutes = Math.floor( (t/1000/60) % 60 );
    var hours = Math.floor( (t/(1000*60*60)) % 24 );
    var days = Math.floor( t/(1000*60*60*24) );
    var percentage = Math.floor(100.0 * (now - start) / all);
    return {
        'total': t,
        'days': days,
        'hours': hours,
        'minutes': minutes,
        'seconds': seconds,
        'percentage' : percentage
    };
}
