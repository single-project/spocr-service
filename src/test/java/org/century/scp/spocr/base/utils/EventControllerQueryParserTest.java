package org.century.scp.spocr.base.utils;

import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.times;

import java.util.List;
import org.century.scp.spocr.event.controllers.EventController;
import org.century.scp.spocr.event.repositories.EventRepositoryImpl;
import org.century.scp.spocr.exceptions.SpocrException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class EventControllerQueryParserTest {
  @Captor ArgumentCaptor<String> keyCaptor;

  @Captor ArgumentCaptor<Object[]> paramsCaptor;

  @Mock EventRepositoryImpl eventRepository;

  @InjectMocks private EventController eventController;

  @Before
  public void init_mocks() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void successfullyVerifyArgs() {
    eventController.doQuery("q>0");

    // verify
    Mockito.verify(eventRepository, times(1)).findAll(keyCaptor.capture(), paramsCaptor.capture());
    List<String> query = keyCaptor.getAllValues();
    List<Object[]> params = paramsCaptor.getAllValues();

    // assert
    Object actualId = params.get(0);
    Assert.assertThat(actualId, instanceOf(Integer.class));
    Assert.assertEquals("q>?", query.get(0));
    Assert.assertEquals(0, (int)actualId);
  }

  @Test
  public void successfullyVerifyArgs2() {
    eventController.doQuery("q>10");

    // verify
    Mockito.verify(eventRepository, times(1)).findAll(keyCaptor.capture(), paramsCaptor.capture());
    List<String> query = keyCaptor.getAllValues();
    List<Object[]> params = paramsCaptor.getAllValues();

    // assert
    Object actualId = params.get(0);
    Assert.assertThat(actualId, instanceOf(Integer.class));
    Assert.assertEquals("q>?", query.get(0));
    Assert.assertEquals(10, (int)actualId);
  }

  @Test(expected = SpocrException.class)
  public void failedVerifyArgsCauseOfNumberFormat() {
    eventController.doQuery("q>a");
  }

  @Test(expected = SpocrException.class)
  public void failedVerifyArgsCauseOfUnknownOperation() {
    eventController.doQuery("q!a");
  }

  @Test(expected = SpocrException.class)
  public void failedVerifyArgsCauseOfNullQuery() {
    eventController.doQuery(null);
  }

  @Test(expected = SpocrException.class)
  public void failedVerifyArgsCauseOfEmptyQuery() {
    eventController.doQuery("");
  }
}
