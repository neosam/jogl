/*
 * Copyright (c) 2003 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 * 
 * - Redistribution of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 * 
 * - Redistribution in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 * 
 * Neither the name of Sun Microsystems, Inc. or the names of
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * 
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES,
 * INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN
 * MIDROSYSTEMS, INC. ("SUN") AND ITS LICENSORS SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN OR
 * ITS LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR
 * DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE
 * DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY,
 * ARISING OUT OF THE USE OF OR INABILITY TO USE THIS SOFTWARE, EVEN IF
 * SUN HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * You acknowledge that this software is not designed or intended for use
 * in the design, construction, operation or maintenance of any nuclear
 * facility.
 * 
 * Sun gratefully acknowledges that this software was originally authored
 * and developed by Kenneth Bradley Russell and Christopher John Kline.
 */

package net.java.games.jogl;

public interface GLAutoDrawable extends GLDrawable, ComponentEvents {
  /**
   * Returns the context associated with this drawable. The returned
   * context will be synchronized.
   */
  public GLContext getContext();

  /** Adds a {@link GLEventListener} to this drawable. If multiple
      listeners are added to a given drawable, they are notified of
      events in an arbitrary order. */
  public void addGLEventListener(GLEventListener listener);

  /** Removes a {@link GLEventListener} from this drawable. Note that
      if this is done from within a particular drawable's {@link
      GLEventListener} handler (reshape, display, etc.) that it is not
      guaranteed that all other listeners will be evaluated properly
      during this update cycle. */
  public void removeGLEventListener(GLEventListener listener);

  /** Causes OpenGL rendering to be performed for this GLAutoDrawable
      by calling {@link GLEventListener#display} for all registered
      {@link GLEventListener}s. Called automatically by the window
      system toolkit upon receiving a repaint() request. this routine
      may be called manually for better control over the rendering
      process. It is legal to call another GLAutoDrawable's display
      method from within {@link GLEventListener#display}. */
  public void display();

  /** Enables or disables automatic buffer swapping for this drawable.
      By default this property is set to true; when true, after all
      GLEventListeners have been called for a display() event, the
      front and back buffers are swapped, displaying the results of
      the render. When disabled, the user is responsible for calling
      {@link #swapBuffers} manually. */
  public void setAutoSwapBufferMode(boolean onOrOff);

  /** Indicates whether automatic buffer swapping is enabled for this
      drawable. See {@link #setAutoSwapBufferMode}. */
  public boolean getAutoSwapBufferMode();

  /** Returns the {@link GL} pipeline object this GLDrawable uses.  If
      this method is called outside of the {@link GLEventListener}'s
      callback methods (init, display, etc.) it may return null. Users
      should not rely on the identity of the returned GL object; for
      example, users should not maintain a hash table with the GL
      object as the key. Additionally, the GL object should not be
      cached in client code, but should be re-fetched from the
      GLDrawable at the beginning of each call to init, display,
      etc. */
  public GL getGL();

  /** Sets the {@link GL} pipeline object this GLDrawable uses. This
      should only be called from within the GLEventListener's callback
      methods, and usually only from within the init() method, in
      order to install a composable pipeline. See the JOGL demos for
      examples. */
  public void setGL(GL gl);

  /** Returns the {@link GLU} pipeline object this GLDrawable uses. */
  public GLU getGLU();

  /** Sets the {@link GLU} pipeline object this GLDrawable uses. */
  public void setGLU(GLU glu);

  /** Indicates whether this drawable is capable of fabricating a
      subordinate offscreen drawable for advanced rendering techniques
      which require offscreen hardware-accelerated surfaces. Note that
      this method is only guaranteed to return a correct result once
      your GLEventListener's init() method has been called. */
  public boolean canCreateOffscreenDrawable();

  /** Creates a subordinate offscreen drawable (pbuffer) for this
      drawable. This routine should only be called if {@link
      #canCreateOffscreenDrawable} returns true. The passed
      capabilities are matched according to the platform-dependent
      pbuffer format selection algorithm, which currently can not be
      overridden. */
  public GLPbuffer createOffscreenDrawable(GLCapabilities capabilities,
                                           int initialWidth,
                                           int initialHeight);
}