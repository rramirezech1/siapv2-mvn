/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function isNumber(event) {
    alert('ok');
    if (event) {
        var charCode = (event.which) ? event.which : event.keyCode;
        if (charCode < 48 || charCode > 57)
            return false;
    }
    return true;
}
;