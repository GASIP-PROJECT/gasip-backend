package com.example.gasip.category.repository;

//@RequiredArgsConstructor
//public class CategoryRepositoryCustomImpl implements CategoryRepositoryCustom {
//
//    private final JPAQueryFactory queryFactory;

//    @Override
//    public List<CategoryDTO> findAllByParentCategory() {
//        return queryFactory
//                .select(Projections.constructor(CategoryDTO.class, category.Id, category.collegeId, category.collegeName, category.majorId, category.majorName, category.parentCategory, category.children))
//                .from(category)
//                .where(isNull(category.parentCategory))
//                .fetch();
//    }

//    @Override
//    public List<CategoryDTO> findAllByParentCategory() {
//        return queryFactory
//                .select(new QCategoryDTO(category.Id, category.collegeId, category.collegeName, category.majorId, category.majorName, category.parentCategory, category.children))
//                .from(category)
//                .where(isNull(category.parentCategory))
//                .fetch();
//    }
//}
