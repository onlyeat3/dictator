package com.github.liuyuyu.dictator.server;

import com.github.liuyuyu.dictator.server.utils.ResourceName;
import lombok.Data;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class MappingScanner extends AbstractSpringBootTest {
    @Autowired private ApplicationContext applicationContext;

    @Test
    public void genMapping(){
        List<Class<?>> validAnnotationList = new ArrayList<>();
        validAnnotationList.add(RequestMapping.class);

        List<Object> controllerList = new ArrayList<>();
        controllerList.addAll(this.applicationContext.getBeansWithAnnotation(Controller.class).values());

        List<SysResource> resourceEntityList = new ArrayList<>();
        AtomicLong id = new AtomicLong();
        for (Object controller : controllerList) {
            Class<?> clazz = controller.getClass();
            RequestMapping[] requestMappings = clazz.getAnnotationsByType(RequestMapping.class);
            String controllerUri = Arrays.stream(requestMappings)
                    .map(requestMapping -> Arrays.stream(requestMapping.value()).findFirst().orElse(StringUtils.EMPTY))
                    .map(s -> !s.startsWith("/") ? "/" + s : s)
                    .findFirst()
                    .orElse(StringUtils.EMPTY);

            Optional<ResourceName> resourceNameOptional = Arrays.stream(controller.getClass().getAnnotationsByType(ResourceName.class)).findFirst();
            if (!resourceNameOptional.isPresent()) {
                continue;
            }
            ResourceName resourceNameAnnotation = resourceNameOptional.get();
            String resourceName = resourceNameAnnotation.value();
            String controllerPath = resourceNameAnnotation.uri();

            SysResource controllerResourceEntity = new SysResource();
            controllerResourceEntity.setId(id.incrementAndGet());
            controllerResourceEntity.setTargetUri(controllerPath);
            controllerResourceEntity.setResourceName(resourceName);
            controllerResourceEntity.setParentId(0L);
            controllerResourceEntity.setParentIds("/0");

            if(resourceEntityList.contains(controllerResourceEntity)){
                continue;
            }

            resourceEntityList.add(controllerResourceEntity);

            List<Method> methodList = Arrays.stream(clazz.getDeclaredMethods())
                    .filter(method -> {
                        for (Annotation annotation : method.getDeclaredAnnotations()) {
                            if(validAnnotationList.contains(annotation.annotationType())){
                                return true;
                            }
                        }
                        return false;
                    })
                    .collect(Collectors.toList());
            for (Method method : methodList) {
                String uri = Arrays.stream(method.getAnnotationsByType(RequestMapping.class))
                        .map(requestMapping1 -> Arrays.stream(requestMapping1.value())
                                .findFirst()
                                .orElse(StringUtils.EMPTY))
                        .findFirst().orElse(StringUtils.EMPTY);
                if(StringUtils.isEmpty(uri)){
                    continue;
                }
                SysResource methodResourceEntity = new SysResource();
                if(!uri.startsWith("/")){
                    uri = "/" + uri;
                }
                uri = controllerUri + uri;
                methodResourceEntity.setResourceName(method.getName());
                methodResourceEntity.setId(id.incrementAndGet());
                methodResourceEntity.setParentId(controllerResourceEntity.getId());
                methodResourceEntity.setParentIds(controllerResourceEntity.getParentId() + "/" + controllerResourceEntity.getId());
                methodResourceEntity.setTargetUri(uri);

                Optional<ResourceName> methodResourceNameOptional = Arrays.stream(method.getAnnotationsByType(ResourceName.class)).findFirst();
                if(!methodResourceNameOptional.isPresent()){
                    continue;
                }
                val resourceName1 = methodResourceNameOptional.get();
                String methodResourceName = methodResourceNameOptional.get().value();
                methodResourceEntity.setResourceName(methodResourceName);
                if(resourceEntityList.contains(methodResourceEntity)){
                    continue;
                }
                resourceEntityList.add(methodResourceEntity);
            }
        }

        for (SysResource sysResourceEntity : resourceEntityList) {
            String sql = String.format("INSERT INTO `dictator_resource` VALUES (%s,'%s', %s, %s, '%s', '%s', '2018-04-24 14:26:46', '2018-04-25 16:02:08', 0, '');"
                    ,sysResourceEntity.getId()
                    , sysResourceEntity.getResourceName()
                    , 1
                    , sysResourceEntity.getParentId()
                    , sysResourceEntity.getParentIds()
                    , sysResourceEntity.getTargetUri()
            );
            System.out.println(sql);
        }
    }

    @Data
    class SysResource {
        /**
         *  自增主键
         */
        private Long id;

        /**
         *  资源名
         */
        private String resourceName;

        /**
         *  资源类型：菜单、按钮
         */
        private Integer resourceType;

        /**
         *  父节点ID
         */
        private Long parentId;

        /**
         *  所有上级节点
         */
        private String parentIds;

        /**
         *  目标URI
         */
        private String targetUri;

        /**
         *  创建时间
         */
        private Date createdAt;

        /**
         *  最后更新时间
         */
        private Date updatedAt;

        /**
         *  后台操作人ID
         */
        private Long operatorId;

        /**
         *  操作者IP
         */
        private String operatorIp;
    }
}
