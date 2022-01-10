<#include "header.ftl">

<b>Welcome to our little demonstration on the VR Webapp</b>

<table id="availableHOs">
	<tr>
		<th>#</th>
		<th>Street</th>
		<th>Town</th>
		<th>Capacity</th>
	</tr>
	<#list availableOffers as ho>
	<tr>
		<td><a href="guestgui?action=selectHolidayOffer&amp;hid=${ho.id}" title="Make Booking">${ho.id}</a></td>
		<td>${ho.addressData.street}</td>
		<td>${ho.addressData.town}</td>
		<td>${ho.capacity}</td>
	</tr>
	</#list>
</table>
<#include "footer.ftl">