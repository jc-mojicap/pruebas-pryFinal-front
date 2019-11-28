var oldAlert = jq.alert;
jq.alert = function (msg, opts) {
	if (msg.indexOf('27112105') > -1){
		alert('El tamaño de los archivos es mayor al aceptado por el sistema.');
	} else if (msg.indexOf('The request was rejected because the file size') > -1) {
            try {
                var oldMsg = msg;
                // get original number
                msg = msg.replace('The request was rejected because the file size (', '');
                msg = msg.replace(') exceeds the configured maximum (', '_');
                msg = msg.replace(')', '');
                var v1 = msg.substring(0, msg.indexOf('_'));
                var v2 = msg.substring(msg.indexOf('_')+1, msg.length);
                // calculate MB
                var n1 = (parseInt(v1)/1024/ 1024).toFixed(2) + ' MB';
                var n2 = (parseInt(v2)/1024/ 1024).toFixed(2) + ' MB';
                // replace original number with MB
                oldMsg = oldMsg.replace(v1, n1);
                oldMsg = oldMsg.replace(v2, n2);

                oldAlert('El archivo tiene un peso de ' + v1 + ' mayor al permitido (' + v2 + '), por favor verifique e intente de nuevo', opts); // use custom message here
            } catch(err) {
                alert('El tamaño de los archivos es mayor al aceptado por el sistema.');
        }
    } else {
        oldAlert(msg, opts);
    }
}