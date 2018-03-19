/*
 * Copyright (C) 2018 Sergio Carabantes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sergio.twitter;

import com.sergio.twitter.data.preferences.PreferenceRepositoryImpl;
import com.sergio.twitter.domain.authentication.GetAuthenticationInteractor;
import com.sergio.twitter.domain.tweets.GetSearchTweetsInteractor;
import com.sergio.twitter.domain.tweets.GetSearchTweetsOutput;
import com.sergio.twitter.domain.tweets.model.SearchMetadata;
import com.sergio.twitter.domain.tweets.model.SearchTweets;
import com.sergio.twitter.tweets.presenter.TweetsPresenter;
import com.sergio.twitter.tweets.view.TweetsView;
import io.reactivex.disposables.Disposables;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TweetsPresenterTest {

    @Mock TweetsView mockTweetsView;
    @Mock GetSearchTweetsInteractor mockGetSearchTweetsInteractor;
    @Mock GetAuthenticationInteractor mockGetAuthenticationInteractor;
    @Mock PreferenceRepositoryImpl mockPreferenceRepository;

    private TweetsPresenter tweetsPresenter;

    @Before
    public void setUp() throws Exception {
        tweetsPresenter = new TweetsPresenter(
                mockGetSearchTweetsInteractor,
                mockGetAuthenticationInteractor,
                mockPreferenceRepository);
        tweetsPresenter.setView(mockTweetsView);
    }

    @Test
    public void should_call_check_authentication_when_the_first_start_up() {
        //given
        when(mockPreferenceRepository.getAccessToken()).thenReturn(null);

        //when
        tweetsPresenter.resume();

        //then
        verify(mockGetAuthenticationInteractor).execute(any());
    }

    @Test
    public void should_call_load_data_when_resume_is_called() {
        //given
        when(mockPreferenceRepository.getAccessToken()).thenReturn("token");

        //when
        tweetsPresenter.resume();

        //then
        verify(mockGetSearchTweetsInteractor).execute(any(), any());
    }

    @Test
    public void should_show_content_when_resume_is_called() {
        //given
        givenCallInSearchTweetsIsSuccessful();
        when(mockPreferenceRepository.getAccessToken()).thenReturn("token");

        //when
        tweetsPresenter.setNewQuery(true);
        tweetsPresenter.resume();

        //then
        verify(mockGetSearchTweetsInteractor).execute(any(), any());
        verify(mockTweetsView).showLoading();
        verify(mockTweetsView).setContent(any());
        verify(mockTweetsView).hideLoading();
    }

    private void givenCallInSearchTweetsIsSuccessful() {
        SearchTweets searchTweets = new SearchTweets();
        searchTweets.setStatuses(new ArrayList<>());
        SearchMetadata searchMetadata = new SearchMetadata();
        searchMetadata.setNextResults("next_results");
        searchTweets.setSearchMetadata(searchMetadata);
        searchTweets.setSearchMetadata(searchMetadata);
        doAnswer(invocation -> {
            ((GetSearchTweetsOutput) invocation.getArgument(1)).onSearchTweetsFetched(searchTweets);
            return Disposables.empty();
        }).when(mockGetSearchTweetsInteractor).execute(any(), any());
    }
}