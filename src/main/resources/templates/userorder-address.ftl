<!-- registration-user.html -->
<input type="hidden" ng-model="octrl.order.userId" />
<input type="hidden" ng-model="octrl.order.restaurantId" />
    <div class="form-group">
        <label style="color:white" for="oaddress">Address</label>
            <input type="text" ng-model="formData.address" id="oaddress" class="form-control" placeholder="Enter address" />
    </div>
    <div class="form-group">
        <label style="color:white" for="ocity">City</label>
            <input type="text" ng-model="octrl.order.ocity" id="ocity" class="form-control" placeholder="Enter the City" />
    </div>
    <div class="form-group">
        <label style="color:white" for="oDeliveryType">Delivery Type</label>
            <input type="text" ng-model="octrl.order.deliveryType" id="deliveryType" class="form-control" placeholder="Enter the DeliveryType" />
    </div>
    <div class="form-group">
        <label style="color:white" for="ophoneNumber">Phone Number</label>
            <input type="text" ng-model="octrl.order.phoneNumber" id="ophoneNumber" class="form-control" placeholder="Enter the PhoneNumber" />
    </div>
    <div class="form-group">
        <label style="color:white" for="oEmail">Email</label>
        <i class="fa fa-lock"></i>
            <input type="text" ng-model="octrl.order.email" id="oEmail" class="form-control" placeholder="Enter the Email" />
        <span ng-show="form.confirmPassword.$dirty && form.password.$error.required" class="help-block">Confirm Password is required</span>
    </div>

<div class="form-group row">
    <div class="col-xs-6 col-xs-offset-3">
        <a ui-sref="userorder.order" class="btn btn-block btn-first-step">
            Next Section <span class="glyphicon glyphicon-circle-arrow-right"></span>
        </a>
    </div>
</div>