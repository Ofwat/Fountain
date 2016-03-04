//The passed params should reallty be an object with default vals?
var Pager = (function(){
	
	console.log("Initialising pager");
	//How do we call the callback if it requires params from outside this object?
	//Curry it?
	var prevEnabled = false;
	var nextEnabled = false;
	var renderInCenter = false;
	var positionWithinListItems = 0;
	var currentPage = 0;
	var handlerNext;
	var handlerPrev;	
	var handlerPage;
	
	//Is this private? - yes
	//var test = function(){
	//		console.log("test");
	//}
	
	//Constructor
	//These are public
	var Pager = function($element, showNavigation, visibleNavItemsCount, totalItems, increment, callback){
		//The element to render the pager in. 
		this.$element = $element;
		//doesn't do anything!
		this.shownavigation = showNavigation;
		//The number of items to show in the pager at any one time e.g the number of pages you can click on! 
		this.visibleNavItemsCount = visibleNavItemsCount;
		//The total number of items?
		this.totalItems = totalItems;
		this.startRow = 0;
		//The increment for each next?
		this.increment = increment;
		//A call back to get the data?
		this.callback = callback;
	}

	//Reset the pager to allow
	Pager.prototype.reset = function(){
		var prevEnabled = false;
		var renderInCenter = false;
		var positionWithinListItems = 0;
		var currentPage = 0;
	}
	
	Pager.prototype.next = function(){
		//test.apply(this); //applying the context to the private function so that we can call it. 
		console.log("next " + nextEnabled);
		currentPage++;
		
		//Calculate the new start row?
		if(currentPage+1 == Math.ceil(this.totalItems/this.increment)){
			nextEnabled = false;
		}else{
			prevEnabled = true;
		}
		
		var sr = this.startRow + this.increment;
		if(sr >= (this.totalItems - this.increment)){
			this.startRow = this.totalItems - this.increment;
		}else{
			this.startRow = sr;
		}
		
		this.callback(this.startRow);
		this.prepare();
	}

	Pager.prototype.gotoPage = function(pageNumber){
		console.log("Goto Page " + pageNumber);
		currentPage = pageNumber
		console.log("Current page is " + currentPage);
		
		
		if(currentPage == 0){
			prevEnabled = false;
		}else{
			prevEnabled = true;
		}
		
		if(currentPage == Math.ceil(this.totalItems/this.increment)){
			nextEnabled = false;
		}else{
			prevEnabled = true;
		}
		
		var sr = this.increment * currentPage;
		this.startRow = sr;
		console.log(this.startRow);		
		this.callback(this.startRow);
		this.prepare();		
		
		
	}
	
	Pager.prototype.previous = function(){
		console.log("prev " + prevEnabled);
		currentPage--;
		
		//Calculate the new start row?
		if(currentPage == 0){
			prevEnabled = false;
		}else{
			prevEnabled = true;
		}
		
		var sr = this.startRow - this.increment;
		if(sr <= 0){
			this.startRow = 0;
		}else{
			this.startRow = sr;
		}
		this.callback(this.startRow);
		this.prepare();
	}

	
	Pager.prototype.render = function(pages, selectedPage){
		//Clear the div.
		this.$element.empty();
		
		if(this.totalItems <= this.increment){
			return;
		}
		
		var liString = '';
		if(currentPage == 0){
			liString = liString + '<li class="disabled prev"><a class="prev" href="#">Prev</a></li>';
			prevEnabled = false;
		}else{
			liString = liString + '<li class="prev"><a class="prev" href="#">Prev</a></li>';
			prevEnabled = true;
		}
		
		
		for(var i=0;i<pages.length;i++){
			if(pages[i] == selectedPage){
				liString = liString + '<li class="active"><a>' + pages[i] + '</a></li>';
				positionWithinListItems = i;
			}else{
				liString = liString + '<li><a>' + pages[i] + '</a></li>';
			}
		}
		
		if((currentPage+1) == Math.ceil(this.totalItems/this.increment)){
			liString = liString + '<li class="disabled next"><a class="next" href="#">Next</a></li>';
			nextEnabled = false;
		}else{
			liString = liString + '<li class="next"><a class="next" href="#">Next</a></li>';
			nextEnabled = true;
		}		
		var paginationStr = '<div class="pagination pagination-centered">' +
		'<ul>' + liString + '</ul></div>';
	

		var $paginationDiv = this.$element.append(paginationStr);
		//Add handlers to prev and next - or do we use existing?
		var self = this;
		
		//This is the click on an individual element. 
		//handlerPage = function(text){return function(){alert(text);};};
		handlerPage = function(e){
			return function(){
				self.gotoPage(parseInt(e))
			};
		};
		
		handlerNext == null;
		handlerPrev == null;
		
		var lis = $paginationDiv.find("li");
		
		$.each(lis, function(i, li) {
			var $li = $(li);
			if($li.hasClass("prev")){
				handlerPrev = $li.click(function(){
					if($li.hasClass("disabled")){
						
					}else{					
						self.previous();
					}
				});		
			} else if($li.hasClass("next")){
				handlerNext = $li.click(function(){
					if($li.hasClass("disabled")){
						
					}else{
						self.next();
					}
					
				});
			} else{
				//Assign a callback using the url?
				$li.click(handlerPage($li.text()));
			}
			
		});		
	}
	
	
	
	Pager.prototype.prepare = function(){
		console.log("Preparing list of pages");
		//var maxStartRow = this.totalItems;
		var maxPages = Math.ceil(this.totalItems/this.increment);
		var currentPageStart = Math.floor(this.startRow/this.increment);
		//How many pages before?
		var pagesBefore = currentPage - 1;
		//How many pages after?
		var pagesAfter = maxPages-currentPage;
		
		//Ok now we just
		var selectedPage; //0 based
		var pages = new Array();
		
		//Should we be 'selecting' the middle page
		
		//Ok are we 0-to the number of max pages displayed
		
		//Calculate if we are displaying the start pages or the end pages.
		var atEnd = false;
		var atBeginnning = false;
		//at beginning?
		if(currentPage < this.visibleNavItemsCount){
			atBeginning = true;
		}else{
			atBeginning = false;
		}
		
		//at end?
		if(((currentPageStart+1) + Math.ceil((currentPageStart+1)/this.increment)) >= maxPages){ //this is wrong. 
			if(maxPages != 2){
				atEnd = true;
			}
		}else{
			atEnd = false;
		}
		
		if(!atBeginning && !atEnd){
			renderIncenter = true;
		}else{
			renderIncenter = false;
		}

		
		//We also need to take into account cases where we are at the start and the end! 
		
		if(atBeginning && !atEnd){
			
			var startPage = 1;
			var endPage = this.visibleNavItemsCount;
			if(maxPages<=endPage){
				endPage = maxPages;
			}			
			
			//for(var i=1;i<=this.visibleNavItemsCount;i++){
			for(var i=1;i<=endPage;i++){
				pages.push(i);
			}
		}
		
		if(atEnd && !atBeginning){
			for(var i=((maxPages+1)-this.visibleNavItemsCount);i<=maxPages;i++){
				pages.push(i);
			}			
		}
		
		if(atBeginning && atEnd){
			//We have only one page to render!
			var startPage = 1;
			var endPage = 1;
			if(endPage>maxPages){
				endPage = maxPages;
			}
			for(var i=startPage;i<endPage;i++){
				pages.push(i);
			}			
		}
		
		if(!atBeginning && !atEnd){
			//Ok we're in the middle - woooah!
			//we need the start and end pages and the current page.
			var startPage = currentPage - Math.ceil(this.visibleNavItemsCount/2);
			var endPage = currentPage + Math.ceil(this.visibleNavItemsCount/2);
			if(endPage>maxPages){
				endPage = maxPages;
			}
			for(var i=startPage;i<endPage;i++){
				pages.push(i);
			}
		}
		
		console.log(pages);
		this.render(pages,currentPage+1);
	}
	return Pager;
}());
