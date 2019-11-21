
import { Constants } from './constant.mjs';

export function callRest(serviceUrl, method, jsonData) {
    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        console.log(`Service response status code: ${this.status}`);
        if (this.readyState == 4 && this.status == 200) {
            console.log(`Service response text: ${this.responseText}`);
            return this.responseText;
        }
    };

    //TODO: see if headers are needed
    //TODO: pass cookie auth

    xhttp.open(method, `${Constants.serverBaseUrl}/${serviceUrl}`, true);
    xhttp.setRequestHeader("Content-type", "application/json");

    console.log(`Calling service: ${Constants.serverBaseUrl}/${serviceUrl}`);
    console.log(`Service JSON data sent: ${jsonData}`);

    if (jsonData != "")
        xhttp.send(jsonData);
}
