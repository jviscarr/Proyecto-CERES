PrimeFaces.widget.GMap=PrimeFaces.widget.DeferredWidget.extend({init:function(a){this._super(a);this.renderDeferred()},_render:function(){this.map=new google.maps.Map(document.getElementById(this.id),this.cfg);this.cfg.fitBounds=!(this.cfg.fitBounds===false);this.viewport=this.map.getBounds();if(this.cfg.markers){this.configureMarkers()}if(this.cfg.polylines){this.configurePolylines()}if(this.cfg.polygons){this.configurePolygons()}if(this.cfg.circles){this.configureCircles()}if(this.cfg.rectangles){this.configureRectangles()}this.configureEventListeners();if(this.cfg.fitBounds&&this.viewport){this.map.fitBounds(this.viewport)}if(this.cfg.infoWindow){var a=this;google.maps.event.addListener(this.cfg.infoWindow,"domready",function(){a.loadWindow(a.cfg.infoWindowContent)})}},getMap:function(){return this.map},getInfoWindow:function(){return this.cfg.infoWindow},loadWindow:function(a){this.jq.find(PrimeFaces.escapeClientId(this.getInfoWindow().id+"_content")).html(a||"")},openWindow:function(c){var a=this.getInfoWindow();var b=this;PrimeFaces.ajax.Response.handle(c,null,null,{widget:a,handle:function(d){b.cfg.infoWindowContent=d;a.setContent('<div id="'+a.id+'_content">'+d+"</div>");a.open(b.getMap(),b.selectedOverlay)}});return true},configureMarkers:function(){var a=this;for(var c=0;c<this.cfg.markers.length;c++){var b=this.cfg.markers[c];b.setMap(this.map);if(this.cfg.fitBounds){this.extendView(b)}google.maps.event.addListener(b,"click",function(d){a.fireOverlaySelectEvent(d,this)});google.maps.event.addListener(b,"dragend",function(d){a.fireMarkerDragEvent(d,this)})}},fireMarkerDragEvent:function(c,a){if(this.hasBehavior("markerDrag")){var b={params:[{name:this.id+"_markerId",value:a.id},{name:this.id+"_lat",value:c.latLng.lat()},{name:this.id+"_lng",value:c.latLng.lng()}]};this.callBehavior("markerDrag",b)}},geocode:function(a){var d=this;if(this.hasBehavior("geocode")){var c=new google.maps.Geocoder(),f=[],b=[],e=[];c.geocode({address:a},function(k,h){if(h==google.maps.GeocoderStatus.OK){for(var j=0;j<k.length;j++){var g=k[j].geometry.location;f.push(g.lat());b.push(g.lng());e.push(k[j].formatted_address)}if(k.length){var l={params:[{name:d.id+"_query",value:a},{name:d.id+"_addresses",value:e.join("_primefaces_")},{name:d.id+"_lat",value:f.join()},{name:d.id+"_lng",value:b.join()}]};d.callBehavior("geocode",l)}}else{PrimeFaces.error("Geocode was not found")}})}},reverseGeocode:function(d,a){var c=this;if(this.hasBehavior("reverseGeocode")){var b=new google.maps.Geocoder(),f=new google.maps.LatLng(d,a),e=[];b.geocode({latLng:f},function(j,g){if(g==google.maps.GeocoderStatus.OK){for(var h=0;h<j.length;h++){if(j[h]){e[h]=j[h].formatted_address}}if(0<e.length){var k={params:[{name:c.id+"_address",value:e.join("_primefaces_")},{name:c.id+"_lat",value:d},{name:c.id+"_lng",value:a}]};c.callBehavior("reverseGeocode",k)}else{PrimeFaces.error("No results found")}}else{PrimeFaces.error("Geocoder failed")}})}},configurePolylines:function(){this.addOverlays(this.cfg.polylines)},configureCircles:function(){this.addOverlays(this.cfg.circles)},configureRectangles:function(){this.addOverlays(this.cfg.rectangles)},configurePolygons:function(){this.addOverlays(this.cfg.polygons)},fireOverlaySelectEvent:function(c,a){this.selectedOverlay=a;if(this.hasBehavior("overlaySelect")){var b={params:[{name:this.id+"_overlayId",value:a.id}]};this.callBehavior("overlaySelect",b)}},configureEventListeners:function(){var a=this;this.cfg.formId=$(PrimeFaces.escapeClientId(this.id)).parents("form:first").attr("id");if(this.cfg.onPointClick){google.maps.event.addListener(this.map,"click",function(b){a.cfg.onPointClick(b)})}this.configureStateChangeListener();this.configurePointSelectListener()},configureStateChangeListener:function(){var a=this,b=function(c){a.fireStateChangeEvent(c)};google.maps.event.addListener(this.map,"zoom_changed",b);google.maps.event.addListener(this.map,"dragend",b)},fireStateChangeEvent:function(c){if(this.hasBehavior("stateChange")){var b=this.map.getBounds();var a={params:[{name:this.id+"_northeast",value:b.getNorthEast().lat()+","+b.getNorthEast().lng()},{name:this.id+"_southwest",value:b.getSouthWest().lat()+","+b.getSouthWest().lng()},{name:this.id+"_center",value:b.getCenter().lat()+","+b.getCenter().lng()},{name:this.id+"_zoom",value:this.map.getZoom()}]};this.callBehavior("stateChange",a)}},configurePointSelectListener:function(){var a=this;google.maps.event.addListener(this.map,"click",function(b){a.firePointSelectEvent(b)})},firePointSelectEvent:function(b){if(this.hasBehavior("pointSelect")){var a={params:[{name:this.id+"_pointLatLng",value:b.latLng.lat()+","+b.latLng.lng()}]};this.callBehavior("pointSelect",a)}},addOverlay:function(a){a.setMap(this.map)},addOverlays:function(b){var a=this;$.each(b,function(c,d){d.setMap(a.map);a.extendView(d);google.maps.event.addListener(d,"click",function(e){a.fireOverlaySelectEvent(e,d)})})},extendView:function(b){if(this.cfg.fitBounds&&b){var a=this;this.viewport=this.viewport||new google.maps.LatLngBounds();if(b instanceof google.maps.Marker){this.viewport.extend(b.getPosition())}else{if(b instanceof google.maps.Circle||b instanceof google.maps.Rectangle){this.viewport.union(b.getBounds())}else{if(b instanceof google.maps.Polyline||b instanceof google.maps.Polygon){b.getPath().forEach(function(d,c){a.viewport.extend(d)})}}}}},checkResize:function(){google.maps.event.trigger(this.map,"resize");this.map.setZoom(this.map.getZoom())},fitBounds:function(b,c){var a=this.map.fitBounds;this.map.fitBounds=google.maps.Map.prototype.fitBounds;this.map.fitBounds(b,c);this.map.fitBounds=a}});