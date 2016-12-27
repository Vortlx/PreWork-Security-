/**
 * Created by lebedevas on 27.12.2016.
 */
function getUrlParameters(url){
    var pars = url.search.substr(1).split("&");

    var res = {};

    for(var i = 0; i < pars.length; i++){
        var curPar = pars[i].split("=");
        res[curPar[0]] = curPar[1];
    }

    return res;
}
