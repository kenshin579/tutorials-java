package controller;

@RequestMapping(value = "/category")
public class CategoryController {
    @Autoˆwired
    private CategoryService categoryService;

    @RequiredLogin
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView allCategoryList() {
        return categoryService.method1();
    }
}