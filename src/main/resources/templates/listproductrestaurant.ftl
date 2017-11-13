<div class="generic-container" style="margin-left: 100px;">
    <div class="panel panel-default">
    <!-- Default panel contents -->
    <div class="panel-heading"><span class="lead">Product Restaurant</span></div>
    <div class="panel-body">
        <div class="formcontainer">
            <div class="alert alert-success" role="alert" ng-if="prctrl.successMessage">{{prctrl.successMessage}}</div>
            <div class="alert alert-danger" role="alert" ng-if="prctrl.errorMessage">{{prctrl.errorMessage}}</div>
            <form ng-submit="prctrl.submit()" name="myForm" class="form-horizontal">
                <input type="hidden" ng-model="prctrl.productRestaurant.id" />
                <input type="hidden" ng-model="prctrl.productRestaurant.restaurantId" id="restaurantId" />
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="uname">Name</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="prctrl.productRestaurant.name" id="uname" class="username form-control input-sm" placeholder="Enter product name" required ng-minlength="1"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="price">Price</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="prctrl.productRestaurant.price" id="price" class="form-control input-sm" placeholder="Enter the price." required ng-minlength="1"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="quantity">Quantity</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="prctrl.productRestaurant.quantity" id="quantity" class="form-control input-sm" placeholder="Enter the quantity." required ng-minlength="1"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="product">Product</label>
                        <div class="col-md-7">
                            <select class="form-control" name="product" ng-model="prctrl.productRestaurant.productId" ng-options="product.id as product.name for product in prctrl.getAllProducts() track by product.id">
                                <option value="">--Select Product--</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-actions floatRight">
                        <input type="submit"  value="{{!prctrl.productRestaurant.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid || myForm.$pristine">
                        <button type="button" ng-click="prctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
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
		                <th>ID</th>
		                <th>NAME</th>
		                <th>PRICE</th>
                        <th>QUANTITY</th>
		                <th>PRODUCTNAME</th>
                        <th>RESTAURANTNAME</th>
		                <th width="100"></th>
		                <th width="100"></th>
		            </tr>
		            </thead>
		            <tbody>
		            <tr ng-repeat="p in prctrl.getAllProductRestaurants()">
		                <td>{{p.id}}</td>
		                <td>{{p.name}}</td>
		                <td>{{p.price}}</td>
		                <td>{{p.quantity}}</td>
                        <td>{{p.productName}}</td>
                        <td>{{p.restaurantName}}</td> 
		                <td><button type="button" ng-click="prctrl.editProductRestaurant(p.id)" class="btn btn-success custom-width">Edit</button></td>
		                <td><button type="button" ng-click="prctrl.removeProductRestaurant(p.id)" class="btn btn-danger custom-width">Remove</button></td>
		            </tr>
		            </tbody>
		        </table>		
			</div>
		</div>
    </div>
</div>