package com.iwant.doilikePro.config;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

public class VisiableThreadPoolTaskExecutor extends ThreadPoolTaskExecutor{

	 private static final long serialVersionUID = -3998127814486620255L;
	
	 private static final Logger logger = LoggerFactory.getLogger(VisiableThreadPoolTaskExecutor.class);
	 
	 @Override
	public void setCorePoolSize(int corePoolSize) {
		
		super.setCorePoolSize(corePoolSize);
	}

	@Override
	public int getCorePoolSize() {
		
		return super.getCorePoolSize();
	}

	@Override
	public void setMaxPoolSize(int maxPoolSize) {
		
		super.setMaxPoolSize(maxPoolSize);
	}

	@Override
	public int getMaxPoolSize() {
		
		return super.getMaxPoolSize();
	}

	@Override
	public void setKeepAliveSeconds(int keepAliveSeconds) {
		
		super.setKeepAliveSeconds(keepAliveSeconds);
	}

	@Override
	public int getKeepAliveSeconds() {
		
		return super.getKeepAliveSeconds();
	}

	@Override
	public void setQueueCapacity(int queueCapacity) {
		
		super.setQueueCapacity(queueCapacity);
	}

	@Override
	public void setAllowCoreThreadTimeOut(boolean allowCoreThreadTimeOut) {
		
		super.setAllowCoreThreadTimeOut(allowCoreThreadTimeOut);
	}

	@Override
	public void setTaskDecorator(TaskDecorator taskDecorator) {
		
		super.setTaskDecorator(taskDecorator);
	}

	@Override
	protected ExecutorService initializeExecutor(ThreadFactory threadFactory,
			RejectedExecutionHandler rejectedExecutionHandler) {
		
		return super.initializeExecutor(threadFactory, rejectedExecutionHandler);
	}

	@Override
	protected BlockingQueue<Runnable> createQueue(int queueCapacity) {
		
		return super.createQueue(queueCapacity);
	}

	@Override
	public ThreadPoolExecutor getThreadPoolExecutor() throws IllegalStateException {
		
		return super.getThreadPoolExecutor();
	}

	@Override
	public int getPoolSize() {
		
		return super.getPoolSize();
	}

	@Override
	public int getActiveCount() {
		return super.getActiveCount();
	}

	@Override
	public void execute(Runnable task) {
		showThreadPoolInfo("1. do execute");
		super.execute(task);
	}

	@Override
	public void execute(Runnable task, long startTimeout) {
		showThreadPoolInfo("2. do execute");
		super.execute(task, startTimeout);
	}

	@Override
	public Future<?> submit(Runnable task) {
		showThreadPoolInfo("1. do submit");
		return super.submit(task);
	}

	@Override
	public <T> Future<T> submit(Callable<T> task) {
		showThreadPoolInfo("2. do submit");
		return super.submit(task);
	}

	@Override
	public ListenableFuture<?> submitListenable(Runnable task) {
		showThreadPoolInfo("1. do submitListenable");
		return super.submitListenable(task);
	}

	@Override
	public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
		showThreadPoolInfo("1. do submitListenable");
		return super.submitListenable(task);
	}

	@Override
	protected void cancelRemainingTask(Runnable task) {
		
		super.cancelRemainingTask(task);
	}

	private void showThreadPoolInfo(String prefix) {
		 ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutor();
		 
		 if(null == threadPoolExecutor) {
			 return;
		 }
		 
		 logger.info("{}, {}, taskCount[{}], activeCount [{}], queueSize [{}]", 
				 this.getThreadNamePrefix(), 
				 prefix,
				 threadPoolExecutor.getTaskCount(),
				 threadPoolExecutor.getCompletedTaskCount(),
				 threadPoolExecutor.getActiveCount(),
				 threadPoolExecutor.getQueue().size());
	 }
	 
	 
	 
	 
}
