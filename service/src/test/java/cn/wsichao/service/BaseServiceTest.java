package cn.wsichao.service; 

import cn.wsichao.pojo.Subject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** 
* BaseService Tester. 
* 
* @author <wsichao> 
* @since <pre>Mar 31, 2019</pre> 
* @version 1.0 
*/ 
public class BaseServiceTest {
    private ApplicationContext applicationContext;
    private SubjectService subjectService;

    @Before
    public void init() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("/beans.xml");
        subjectService = applicationContext.getBean(SubjectService.class);
    }

    @After
    public void after() throws Exception {
    }

    /**
    *
    * Method: insert(T pojo)
    *
    */
    @Test
    public void testInsertIsExistedSelectOneDelete() throws Exception {
    //TODO: Test goes here...
        Subject pojo = new Subject();
        pojo.setName("_testName");
        int result = subjectService.insert(pojo);
        Assert.assertSame(1, result);

        boolean boo = subjectService.isExisted(pojo);
        Assert.assertSame(true, boo);

        pojo = subjectService.selectOne(pojo);
        Assert.assertNotNull(pojo);

        int res = subjectService.delete(pojo.getId());
        Assert.assertSame(1, res);
    }

    /**
    *
    * Method: update(T pojo)
    *
    */
    @Test
    public void testUpdate() throws Exception {
        Subject subject = new Subject();
        subject.setName("_testName");
        int result = subjectService.insert(subject);
        Assert.assertSame(result, 1);

        subject = subjectService.selectOne(subject);
        Assert.assertEquals(subject.getName(), "_testName");
        subject.setName("_testNameUpdate");

        int res = subjectService.update(subject);
        Assert.assertSame(res, 1);

        subject = subjectService.selectOne(subject.getId());
        Assert.assertEquals(subject.getName(), "_testNameUpdate");

        Assert.assertSame(subjectService.delete(subject.getId()), 1);
    }

    /**
    *
    * Method: selectList()
    *
    */
    @Test
    public void testSelectList() throws Exception {
    //TODO: Test goes here...

    }

    /**
    *
    * Method: selectList(T pojo)
    *
    */
    @Test
    public void testSelectListPojo() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: selectList(T pojo, String orderBy)
    *
    */
    @Test
    public void testSelectListForPojoOrderBy() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: page(int pageNum, int pageSize, T pojo)
    *
    */
    @Test
    public void testPageForPageNumPageSizePojo() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: page(int pageNum, int pageSize, T pojo, String orderBy)
    *
    */
    @Test
    public void testPageForPageNumPageSizePojoOrderBy() throws Exception {
    //TODO: Test goes here...
    }



    /**
    *
    * Method: createInstanceAndSetId(Long id)
    *
    */
    @Test
    public void testCreateInstanceAndSetId() throws Exception {
    //TODO: Test goes here...
    /*
    try {
       Method method = BaseService.getClass().getMethod("createInstanceAndSetId", Long.class);
       method.setAccessible(true);
       method.invoke(<Object>, <Parameters>);
    } catch(NoSuchMethodException e) {
    } catch(IllegalAccessException e) {
    } catch(InvocationTargetException e) {
    }
    */
    }

} 
