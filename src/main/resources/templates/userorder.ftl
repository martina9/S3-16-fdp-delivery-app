<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Product </span></div>
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" role="alert" ng-if="octrl.successMessage">{{octrl.successMessage}}</div>
	            <div class="alert alert-danger" role="alert" ng-if="octrl.errorMessage">{{octrl.errorMessage}}</div>
	            <form ng-submit="octrl.submit()" name="myForm" class="form-horizontal">
                    <input type="hidden" ng-model="octrl.order.userId" />
                    <input type="hidden" ng-model="octrl.order.restaurantId" />
                    public List<Product> products;
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="oaddress">Address</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="octrl.order.address" id="oaddress" class="username form-control input-sm" placeholder="Enter address" />
	                        </div>
	                    </div>
	                </div>
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="ocity">City</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="octrl.order.ocity" id="ocity" class="username form-control input-sm" placeholder="Enter the City" />
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="oDeliveryType">DeliveryType</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="octrl.order.deliveryType" id="deliveryType" class="username form-control input-sm" placeholder="Enter the DeliveryType" />
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="ophoneNumber">City</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="octrl.order.phoneNumber" id="ophoneNumber" class="username form-control input-sm" placeholder="Enter the PhoneNumber" />
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-12"
                            <label class="col-md-2 control-lable" for="oEmail">Email</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="octrl.order.email" id="oEmail" class="username form-control input-sm" placeholder="Enter the Email" />
                            </div>
                        </div>
                    </div>

	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="category">Restaurant</label>
	                        <div class="col-md-7">
                                <select size="6" name="category" ng-model="octrl.selectedRestaurant"  ng-options="restaurant.id as restaurant.name for restaurant in octrl.getAllRestaurants() track by restaurant.id">
                                    <option value="">--Select Restaurant--</option>
                                </select>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-actions floatRight">
	                        <input type="button"  value="Search" class="btn btn-primary btn-sm" ng-click="octrl.searchProductRestaurant(octrl.selectedRestaurant)">
	                        <#--<button type="button" ng-click="octrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>-->
	                    </div>
	                </div>
	            </form>
    	    </div>
		</div>	
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Products </span></div>
		<div class="panel-body">
			<div class="table-responsive">
		        <table class="table table-hover">
		            <thead>
		            <tr>
                        <th>SELECT</th>
		                <th>ID</th>
		                <th>NAME</th>
		                <th>QUANTITY</th>
		                <th>PRICE</th>
		            </tr>
		            </thead>
		            <tbody>
		            <tr ng-repeat="p in octrl.getAllProductRestaurants()">
                        <td> <input type="checkbox" ng-model="octrl.ids[p.id]" ng-true-value="{{p.id}}"></td>
						<td>{{p.id}}</td>
		                <td>{{p.name}}</td>
                        <td><input type="text" ng-model="p.quantity" id="pquantity" class="username form-control input-sm" placeholder="Enter the quantity" required ng-minlength="1"/> </td>
		                <td>{{p.price}}
		              </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td><button type="button" ng-click="octrl.confirmOrder()" class="btn btn-danger custom-width">Confirm Order</button></td>
                    </tr>
		            </tbody>
		        </table>
                <span ng-repeat='id in octrl.ids'>{{id}}</span></td>
			</div>
		</div>
    </div>
</div>