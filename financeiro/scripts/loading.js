function carregando(){
	var l = document.getElementById('loading');
	var li = document.getElementById('loading-image');
	var h = document.body.clientHeight;
	var w = document.body.clientWidth;
	setDisplay(l, w, h, null, null);
	setDisplay(li, null, null, h / 2, w / 2);
}
function carregado(){
	var l = document.getElementById('loading');
	var li = document.getElementById('loading-image');
	l.style.display="none";
	li.style.display="none";
}

function setDisplay(node, w, h, top, left){
	if (node && node.style) {
		var style = node.style;
		style.position = 'fixed';
		style.display = "block";
		if (h) {
			style.height = h + 'px';
		}
		if (w) {
			style.width = w + 'px';
		}
		if (top) {
			style.top = (top - node.clientHeight / 2) + 'px';
		}
		else{
			style.top = '0px';
		}
		if (left) {
			style.left = (left - node.clientWidth / 2) + 'px';
		}
		else{
			style.left = '0px';
		} 
	}
}